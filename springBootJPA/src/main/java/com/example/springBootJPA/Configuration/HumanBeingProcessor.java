package com.example.springBootJPA.Configuration;

import com.example.springBootJPA.Entity.HumanBeing;
import org.springframework.batch.item.ItemProcessor;

public class HumanBeingProcessor implements ItemProcessor<HumanBeing,HumanBeing> {

    @Override
    public HumanBeing process(HumanBeing HumanBeing) throws Exception {

        HumanBeing.setFirstName(HumanBeing.getFirstName().toUpperCase());
        HumanBeing.setLastName(HumanBeing.getLastName().toUpperCase());

        return HumanBeing;
    }


}
