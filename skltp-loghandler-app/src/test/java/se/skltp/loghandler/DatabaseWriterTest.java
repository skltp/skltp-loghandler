package se.skltp.loghandler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import se.skltp.loghandler.models.dao.*;
import se.skltp.loghandler.models.entity.Anslutning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by parlin on 2017-10-06.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource("classpath:application.properties")
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
            anslutning.setTjanstekontrakt(tjanstekontraktDao.getByNameCreateIfNew("Kontrakt:" + i).getId());
            anslutning.setKallsystem(kallsystemDao.getByNameCreateIfNew("Kallsystem:" + i ).getId());
            anslutning.setKategori(kategoriDao.getByNameCreateIfNew("Kategori:" + i).getId());
            anslutning.setOrganisatoriskenhet(organisatoriskenhetDao.getByNameCreateIfNew("OrganisatoriskEnhet:" +i).getId());
            anslutning.setUrsprungligkonsument(ursprungligkonsumentDao.getByNameCreateIfNew("UrsprungligKonsument:"+i).getId());
            anslutning.setVardenhet(vardenhetDao.getByNameCreateIfNew("Vardenhet:" +i).getId());
            anslutning.setVardgivare(vardgivareDao.getByNameCreateIfNew("Vardgivare:" +i).getId());
            anslutning.setSenasteAnslutningsDatum(new Date());
            anslutning.setForstaAnslutningsDatum(new Date());
            anslutningar.add(anslutning);
        }

        return anslutningar;
    }

}