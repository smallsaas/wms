package com.jfeat.am.module.state.api;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.state.services.crud.service.CountryService;
import com.jfeat.am.module.state.services.persistence.model.Country;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-01-25
 */
@RestController
@RequestMapping("/api/pub/states")
public class CountryEndpoint extends BaseController {

    @Resource
    CountryService countryService;

    @ApiOperation(value = "获取国家列表", response = Country.class)
    @GetMapping
    public Tip getCountryList(@RequestParam(required = true, defaultValue = "en") String lang) {
        return SuccessTip.create(countryService.retrieveMasterList(lang));
    }

    @PostMapping
    public Tip createCountry(@RequestBody Country entity) {
        return SuccessTip.create(countryService.createMaster(entity));
    }

    @GetMapping("/{id}")
    public Tip getCountry(@PathVariable Long id) {
        return SuccessTip.create(countryService.retrieveMaster(id));
    }

    @PutMapping("/{id}")
    public Tip updateCountry(@PathVariable Long id, @RequestBody Country entity) {
        entity.setId(id);
        return SuccessTip.create(countryService.updateMaster(entity));
    }

    public Tip deleteCountry(@PathVariable Long id) {
        return SuccessTip.create(countryService.deleteMaster(id));
    }
}
