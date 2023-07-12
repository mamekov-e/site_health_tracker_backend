package kz.sitehealthtracker.site_health_tracker.service.impls;

import kz.sitehealthtracker.site_health_tracker.config.exception.BadRequestException;
import kz.sitehealthtracker.site_health_tracker.config.exception.NotFoundException;
import kz.sitehealthtracker.site_health_tracker.model.Site;
import kz.sitehealthtracker.site_health_tracker.repository.SiteRepository;
import kz.sitehealthtracker.site_health_tracker.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteRepository siteRepository;


    @Override
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    @Override
    public Site getSiteById(Long id) {
        Optional<Site> siteOptional = siteRepository.findById(id);
        if (siteOptional.isEmpty()) {
            throw NotFoundException.entityNotFoundById(Site.class.getSimpleName(), id);
        }

        return siteOptional.get();
    }

    @Override
    public Site updateSite(Site updatedSite) {
        boolean siteUpdatedUrlAlreadyExist = siteRepository.existsSitesByUrlAndIdIsNot(updatedSite.getUrl(), updatedSite.getId());
        if (siteUpdatedUrlAlreadyExist) {
            throw BadRequestException.entityWithFieldValueAlreadyExist(Site.class.getSimpleName(), updatedSite.getUrl());
        }

        return siteRepository.save(updatedSite);
    }

    @Override
    public void addSite(Site site) {
        boolean siteUrlAlreadyExist = siteRepository.existsSitesByUrlIsIgnoreCase(site.getUrl());
        if (siteUrlAlreadyExist) {
            throw BadRequestException.entityWithFieldValueAlreadyExist(Site.class.getSimpleName(), site.getUrl());
        }

        siteRepository.save(site);
    }

    @Override
    public boolean deleteSiteById(Long id) {
        Optional<Site> siteOptional = siteRepository.findById(id);
        if (siteOptional.isEmpty()) {
            throw NotFoundException.entityNotFoundById(Site.class.getSimpleName(), id);
        }

        siteRepository.deleteById(id);
        return true;
    }

}