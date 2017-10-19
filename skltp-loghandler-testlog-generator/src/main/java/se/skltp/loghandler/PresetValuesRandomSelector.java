package se.skltp.loghandler;



import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.Random;


/**
 * Created by martul on 2017-10-17.
 */
public class PresetValuesRandomSelector {
    private Random random;
    private PresetValuesConfig config;
    private  SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S");

    public PresetValuesRandomSelector(PresetValuesConfig config){
        this.config = config;
        this.random = new Random();
    }

    public String getRandomVargivare(){
        return config.vardgivare.get(random.nextInt(config.vardgivare.size()));
    }

    public String getRandomVardenhet(){
        return config.vardenhet.get(random.nextInt(config.vardenhet.size()));
    }

    public String getRandomOrganisatoriskenhet(){
        return config.organisatoriskenhet.get(random.nextInt(config.organisatoriskenhet.size()));
    }

    public String getRandomKatrgori(){
        return config.katrgori.get(random.nextInt(config.katrgori.size()));
    }

    public String getRandomKallsystem(){
        return config.kallsystem.get(random.nextInt(config.kallsystem.size()));
    }

    public String getRandomTjanstekontrakt(){
        return config.tjanstekontrakt.get(random.nextInt(config.tjanstekontrakt.size()));
    }

    public String getRandomUrsprungligkonsument(){
        return config.ursprungligkonsument.get(random.nextInt(config.ursprungligkonsument.size()));
    }

    public String getRandomDate() {
        return spf.format(new Date());
    }

    public int getRandomCount(){
        return  random.nextInt(30);
    }

}
