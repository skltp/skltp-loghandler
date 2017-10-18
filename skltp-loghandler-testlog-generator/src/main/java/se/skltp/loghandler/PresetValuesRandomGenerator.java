package se.skltp.loghandler;



import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.Random;


/**
 * Created by martul on 2017-10-17.
 */
public class PresetValuesRandomGenerator {
    private Random randomGenerator;
    private PresetValuesConfig config;

    private  SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss,S");

    public PresetValuesRandomGenerator(PresetValuesConfig config){
        this.config = config;
        this.randomGenerator = new Random();
    }


    public String getRandomVargivare(){
        return config.vardgivare.get(randomGenerator.nextInt(config.vardgivare.size()));
    }

    public String getRandomVardenhet(){
        return config.vardenhet.get(randomGenerator.nextInt(config.vardenhet.size()));
    }

    public String getRandomOrganisatoriskenhet(){
        return config.organisatoriskenhet.get(randomGenerator.nextInt(config.organisatoriskenhet.size()));
    }

    public String getRandomKatrgori(){
        return config.katrgori.get(randomGenerator.nextInt(config.katrgori.size()));
    }

    public String getRandomKallsystem(){
        return config.kallsystem.get(randomGenerator.nextInt(config.kallsystem.size()));
    }

    public String getRandomTjanstekontrakt(){
        return config.tjanstekontrakt.get(randomGenerator.nextInt(config.tjanstekontrakt.size()));
    }

    public String getRandomUrsprungligkonsument(){
        return config.ursprungligkonsument.get(randomGenerator.nextInt(config.ursprungligkonsument.size()));
    }

    public String getRandomDate() {
        return spf.format(new Date());
    }

}
