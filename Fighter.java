public class Fighter {
    public String name;
    public int health, damagePerAttack;
    
    public Fighter(String name, int health, int damagePerAttack) {
        this.name = name;
        this.health = health;
        this.damagePerAttack = damagePerAttack;
    }
    public boolean isDefeated(){
        if(health<=0){
            return true;    
        }
        return false;
    }
}
