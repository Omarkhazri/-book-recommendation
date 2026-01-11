package com.sesame.pds.controller.base;

import com.sesame.pds.service.base.BaseService;

/**
 * @author KHAZRI OMAR
 * @since 03/11/2022
 */
public interface BaseController<Service extends BaseService> {
    Service getService();
}
