package sources;


import java.awt.*;


import cflowe.extension.*;
import sources.gui.screengui;




public class level {
    

    game frame;
    level(game frame){
        this.frame = frame;
    }

    long level_begin_time = 0;
    boolean is_boss_killed_flag = false;


    {
    
    }
    
    

    int level_init(){

        level_begin_time = publics.nowtime();
    

        this.frame.player_obj.max_hp = player.standard_hp + (this.frame.player_obj.wings_level - 1);
        this.frame.player_obj.hp = this.frame.player_obj.max_hp;

        return 0;
    }
    

    boolean level_pause = false;


    long player_fly_next = 0;

    boolean is_animation_play_flag = false;
    long begin_count_time = 0;

    screengui screengui_obj = new screengui();

    int level_proc(Graphics pen){

        if (level_pause){
            return 1;
        }

    
        this.frame.background_obj.draw(pen);

        // game over
        if (game.game_over_flag){

            this.frame.reset();

            int reward = Math.abs(Math.round(this.frame.player_obj.score / 100));

            if (reward > 5){
                this.frame.level_reward = math.random(reward - 5, reward + 5);
            } else {
                this.frame.level_reward = 1;
            }
            this.frame.player_obj.coin += this.frame.level_reward;

            game.game_over_flag = false;
            this.frame.game_state = game.state_game_over;


        } 
        

        this.frame.player_obj.display();
        

        // display enemies & draw
        if (!(enemy.objects == null) && enemy.objects.size() > 0){
            for (int _a = 0; _a < enemy.objects.size(); _a++){
                enemy e = (enemy) enemy.objects.get(_a); 
                e.draw(pen, this.frame.camera_obj);
                e.y += (e.speed / 10) + game.loop_speed; // <>speed

                // if (e instanceof boss){
                //     pen.drawImage(
                //         // screengui_obj.health_bar_imgs.get(screengui_obj.gui_get_health_bar(e.hp)),
                //         (int) e.x + Math.round(e.width / 2),
                //         (int) e.y + e.height + 20,
                //         null
                //     );
                // }

                

                // if hit player
                if (math.distance(
                    e.x, 
                    e.y, 
                    this.frame.player_obj.x + Math.round(this.frame.player_obj.width / 2), 
                    this.frame.player_obj.y
                    ) < this.frame.player_obj.collide_shape
                    ){
                        if (!(e instanceof not_damagable)){
                            this.frame.player_obj.hurt(e.damage); 
                            if (e.number_id == missile.missile_id){
                                // this.frame.camera_obj.shake_camera();
                            }

                            
                        


                            enemy.kill(e, pen);
                        }
                    }

                // rescue
                else if (e instanceof not_damagable && e instanceof passive){
                    if (math.distance(
                        e.x, 
                        e.y, 
                        this.frame.player_obj.x + Math.round(this.frame.player_obj.width / 2), 
                        this.frame.player_obj.y
                        ) < this.frame.player_obj.detect_range
                        ){
                        if (e instanceof human){
                            people p = (people) e;
                            // <>sugar
                            if (p.begin_time == 0){
                                p.begin_time = publics.nowtime();
                                
                            }
                        
                            p.rescue_progress = (int) (publics.nowtime() - p.begin_time) / 50; // 5 - 6 sec
                            pen.drawImage(
                                p.progress_bar_img.get(p.rescue_progress > 99 ? 0 : Math.round(p.rescue_progress / 10)), 
                                (int) p.x - 35, 
                                (int) p.y - 15, 
                                null
                            );
                            if (this.frame.particle_effect){
                                pen.setColor(Color.RED);

                                pen.drawLine(
                                    (int) p.x + Math.round(p.width / 2) - 10, 
                                    (int) p.y, 
                                    (int) (this.frame.player_obj.x) + Math.round(this.frame.player_obj.width / 2) - 15, 
                                    (int) (this.frame.player_obj.y) 
                                );

                                pen.setColor(window.pen_color);
                            }
                            
                            if (p.rescue_progress > 99){
                                // rescue suscess
                                enemy.remove(p);
                                this.frame.player_obj.score += 100;
                                this.frame.player_obj.saved_people += 1;
                            }
                        }
                    } else {
                        if (e instanceof human){
                            people p = (people) e;
                            p.begin_time = 0;
                        }
                    }

                }
                // if hit bullet
                for (bullet b: bullet.bullets){
                    if (math.distance(
                        e.x + Math.round(e.width / 2), 
                        e.y + Math.round(e.height / 2),
                        b.x, 
                        b.y
                        ) < 30 && !(e instanceof passive)){
                        bullet.remove(b);
                        e.hp -= b.damage; 
                        if (e.hp < 1){
                            if (e.valued > 0){
                                e.drop_item(e, e.x, e.y);
                            }

                            if (e.number_id == destroyer.destroyer_id){
                                is_boss_killed_flag = true;
                            }
                            // System.out.println(e.number_id);



                            enemy.kill(e, pen);
                            this.frame.player_obj.score += e.valued;
                            e.valued -= e.valued;

                        }
                    }
                }


                enemy ie = (enemy) (enemy.objects.get(enemy.objects.indexOf(e))); 
                ie.animate(((int) this.frame.player_obj.x));
            
            }
        }

        // draw player 
        this.frame.player_obj.draw(pen);

        

        // game over animation
        if (is_boss_killed_flag){
            
            
            spawner.next_spawn = publics.nowtime() + 10000;


            if (!(is_animation_play_flag)){
                is_animation_play_flag = true;
                
                enemy.kill_all(pen);

                System.out.println("boss been killed");
            }

            

            // for (int i = 0; i < window.HEIGHT * 2; i++){
                
            //     this.frame.player_obj.y -= 1;
            // }

            
            // if (enemy.objects.size() < 1 && publics.nowtime() > begin_count_time){
                game.game_over_flag = true;
                 
                is_boss_killed_flag = false;
            // }
        }



        // display bullets
        for (bullet b: bullet.bullets){
            b.draw(pen, this.frame.camera_obj);
            b.y -= 1 + game.loop_speed; // <>speed
            if (b.y < 0){
                b.x = 1000;
            }
        } 

    
        


    
        // draw particle
        
            for (int _p = 0; _p < particle.particles.size(); _p++){
                particle p = particle.particles.get(_p);
                p.draw(pen);
            }
        
        


        // if player hit item
        // display item
        if (true){
            for (int _i = 0; _i < item.items.size(); _i++){
                item i = item.items.get(_i);




                i.draw(pen, this.frame.camera_obj);

                i.y += 1 + game.loop_speed;
                if (
                    math.distance(i.x, i.y, this.frame.player_obj.x, this.frame.player_obj.y)
                    < this.frame.player_obj.collide_shape
                ){
                    i.use(this.frame.player_obj);
                    item.remove(i);

                }
            }
        }

        
        this.frame.background_obj.show_text("SCORE: " + this.frame.player_obj.score, 30, 70, pen);
        this.frame.background_obj.show_text("MSPT: " + game.MSPT, window.WIDTH - 200, 70, pen);


        
        this.frame.spawner_obj.do_random_generation();

        // if object went out of screen, remove it
        // limit y position
        for (int _e = 0; _e < enemy.objects.size(); _e++){
            enemy e = (enemy) enemy.objects.get(_e); 
            if (e.y > window.WIDTH  + (e.height * 2)){
                enemy.objects.remove(e);
            }

        }
        for (int _b = 0; _b < bullet.bullets.size(); _b++){
            bullet b = bullet.bullets.get(_b);
            if (b.y < 0 + b.height * 2){
                bullet.bullets.remove(b);
            }
        }
        for (int _i = 0; _i < item.items.size(); _i++){
            item i = item.items.get(_i);
            if (i.y < 0 + i.height * 2){
                item.items.remove(i);
            }
        }

        return 0;
        
        // event_pool.reset();

  
    
    }
}




class spawner {

    game frame;
    spawner(game frame){
        this.frame = frame;
    }

    static long next_spawn = 0;

    boolean is_boss_spawn_flag = false;
    
    int do_random_generation(){
        // do spawn

        if (game.game_over_flag){
            return 1;
        }




        if (publics.nowtime() > next_spawn){
            next_spawn = publics.nowtime() + math.random(2000, 5000);
            boolean spawn_flag = true;
            enemy next_enemy;

            // 100000
            if (!(is_boss_spawn_flag) && publics.nowtime() - this.frame.level_obj.level_begin_time > 10000){
                is_boss_spawn_flag = true;
                System.out.println("boss spawned");
                enemy.append(new destroyer());
            }
        

            // randomally create enemy
            for (int c = 0; c < math.random(3, 5); c++){
                next_enemy = enemy.enemy_choice(
                    new boomber(),
                    new missile(), 
                    new small_fo(),
                    new space_pawn()
                    
                    
                );
        

                for (object _e: enemy.objects){
                    enemy e = (enemy) _e; 
                    // <>temp spawn limit
                    if (math.distance(next_enemy.x, next_enemy.y, e.x, e.y) < enemy.density + e.width){
                        spawn_flag = false;

                    }
                }
                if (spawn_flag){
                    if (!(next_enemy.ground)){
                        enemy.objects.add(next_enemy);
                    } else if (next_enemy.ground){
                        enemy.objects.add(0, next_enemy);

                    }
                } else if (!(spawn_flag)){
                    spawn_flag = true;
                }
            }

            // 100%
            for (int _e = 0; _e < math.random(3, 5); _e++){
                enemy next_enemy_ii = new space_pawn();
                enemy.objects.add(next_enemy_ii);
            }

            // 10%
            // DO random building / prop generation
            for (int _e = 0; _e < 1; _e++){
                if (math.chance(10)){
                    enemy next_enemy_iii = new space_rental();
                    enemy.objects.add(next_enemy_iii);
                } else if (math.chance(30)){
                    enemy next_enemy_iii = new refugee();
                    enemy.objects.add(next_enemy_iii);
                } else if (math.chance(30)){
                    enemy next_enemy_iii = new tree();
                    enemy.objects.add(next_enemy_iii);
                }
            }

        }
        
        

        return 0;
    }

    


}


