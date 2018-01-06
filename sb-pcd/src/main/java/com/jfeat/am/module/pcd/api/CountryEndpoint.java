package com.jfeat.am.module.pcd.api;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.pcd.services.persistence.model.Country;
import com.jfeat.am.module.pcd.services.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 *  api
 * </p>
 *
 * @author Code Generator
 * @since 2017-12-22
 */
@RestController
@RequestMapping("/api/pub/countries")
public class CountryEndpoint extends BaseController {

    @Resource
    CountryService countryService;

    @GetMapping("/empty")
    public Tip getEmptyCountry() {
        return SuccessTip.create(new Country());
    }

    @GetMapping
    public Tip queryCountries() {
        return SuccessTip.create(countryService.retrieveMasterList());
    }
}
