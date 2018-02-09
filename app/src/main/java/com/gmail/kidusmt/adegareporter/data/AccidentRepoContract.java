package com.gmail.kidusmt.adegareporter.data;

import java.util.List;

/**
 * Created by KidusMT on 2/9/2018.
 */

public interface AccidentRepoContract {

    List<Accident> getAccidents();

    void addAccidents(Accident accident);

    boolean isEmpty();
}
