package kz.sitehealthtracker.site_health_tracker.service;

import kz.sitehealthtracker.site_health_tracker.model.Site;
import kz.sitehealthtracker.site_health_tracker.model.SiteGroup;

import java.util.List;

public interface SiteGroupService {
    List<SiteGroup> getAllSiteGroups();

    List<Site> getAllGroupSitesById(Long id);

    SiteGroup getSiteGroupById(Long id);

    void addSiteGroup(SiteGroup siteGroup);

    void addSitesToGroupById(List<Site> siteList, Long id);

    SiteGroup updateSiteGroup(SiteGroup siteGroup);

    void deleteSiteGroupById(Long id);

    void deleteSitesFromGroupById(List<Site> siteList, Long id);

}