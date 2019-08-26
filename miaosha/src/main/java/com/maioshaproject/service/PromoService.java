package com.maioshaproject.service;

import com.maioshaproject.service.model.PromoModel;
import org.springframework.stereotype.Service;

public interface PromoService {

    PromoModel getPromoByItemId(Integer itemId);

}
