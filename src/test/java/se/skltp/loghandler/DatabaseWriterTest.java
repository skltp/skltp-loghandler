package se.skltp.loghandler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.skltp.loghandler.models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by parlin on 2017-10-06.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DatabaseWriterTest {

    @Autowired
    private DatabaseWriter databaseWriter;

    @Autowired
    private AnslutningDao anslutningDao;

    @Autowired
    private TjanstekontraktDao tjanstekontraktDao;

    @Autowired
    private KallsystemDao kallsystemDao;

    @Autowired
    private KategoriDao kategoriDao;

    @Autowired
    private OrganisatoriskenhetDao organisatoriskenhetDao;

    @Autowired
    private UrsprungligkonsumentDao ursprungligkonsumentDao;

    @Autowired
    private VardenhetDao vardenhetDao;

    @Autowired
    private VardgivareDao vardgivareDao;

    @Test
    public void testHandleAnslutningar() throws Exception {
        LogpostParserService.addAnlutningar(getMockAnslutningar());
        databaseWriter.handleAnslutningar();
        Assert.assertEquals(3, anslutningDao.getAll().size());
    }

    private List<Anslutning> getMockAnslutningar() {
        List<Anslutning> anslutningar = new ArrayList<>();
        for (int i = 0; i < 3 ; i++) {
            Anslutning anslutning = new Anslutning();
            anslutning.setTjanstekontrakt(tjanstekontraktDao.getByNameCreateIfNew("Kontrakt:" + i));
            anslutning.setKallsystem(kallsystemDao.getByHSAIdCreateIfNew("Kallsystem:" + i ));
            anslutning.setKategori(kategoriDao.getByKategoriCreateIfNew("Kategori:" + i));
            anslutning.setOrganisatoriskenhet(organisatoriskenhetDao.getByHSAIdCreateIfNew("OrganisatoriskEnhet:" +i));
            anslutning.setUrsprungligkonsument(ursprungligkonsumentDao.getByHSAIdCreateIfNew("UrsprungligKonsument:"+i));
            anslutning.setVardenhet(vardenhetDao.getByHSAIdCreateIfNew("Vardenhet:" +i));
            anslutning.setVardgivare(vardgivareDao.getByHSAIdCreateIfNew("Vardgivare:" +i));
            anslutning.setYoungest(new Date());
            anslutning.setOldest(new Date());
            anslutningar.add(anslutning);
        }

        return anslutningar;
    }

}