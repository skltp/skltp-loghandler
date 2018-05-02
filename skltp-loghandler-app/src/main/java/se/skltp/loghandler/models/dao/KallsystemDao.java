package se.skltp.loghandler.models.dao;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import se.skltp.loghandler.models.entity.Kallsystem;

@Repository
@Transactional
public class KallsystemDao extends AnslutningChildDao<Kallsystem> {

    @Cacheable("kallsystem")
    public Kallsystem getByNameCreateIfNew(String name) {
        return super.getByNameCreateIfNew(name);
    }

    @Override
    protected Kallsystem getNewObject() {
        return new Kallsystem();
    }

    @Override
    protected String getNameQueryName() {
        return "KallsystemByName";
    }

    @Override
    protected String getNameQueryId() {
        return "KallsystemById";
    }

    @Override
    protected Class<Kallsystem> getEntityClass() {
        return Kallsystem.class;
    }

}
