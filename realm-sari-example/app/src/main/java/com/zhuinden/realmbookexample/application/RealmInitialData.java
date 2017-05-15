package com.zhuinden.realmbookexample.application;


import com.zhuinden.realmbookexample.data.entity.Sari;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class RealmInitialData implements Realm.Transaction {
    @Override
    public void execute(Realm realm) {
        Sari sari = new Sari();

        sari.setId(1);
        sari.setTitle("Muktha Sari");
        sari.setDescription("This is the Description about Muktha Sari.");
        sari.setPrice("2500");
        sari.setImageUrl("http://akrutisarees.com/wp-content/uploads/2016/07/online_embroidery_sarees_muk_1035.jpg");
        realm.insertOrUpdate(sari);

        sari.setId(2);
        sari.setTitle("Monika Sari");
        sari.setDescription("This is the Description about Monika Sari.");
        sari.setPrice("2500");
        sari.setImageUrl("http://akrutisarees.com/wp-content/uploads/2016/07/online_embroidery_sarees_muk_1020.jpg");
        realm.insertOrUpdate(sari);

        sari.setId(3);
        sari.setTitle("Mehak Sari");
        sari.setDescription("This is the Description about Mehak Sari.");
        sari.setPrice("2500");
        sari.setImageUrl("http://akrutisarees.com/wp-content/uploads/2016/07/online_embroidery_sarees_muk_1026.jpg");
        realm.insertOrUpdate(sari);

        sari.setId(4);
        sari.setTitle("Mastani Flevour");
        sari.setDescription("This is the Description about Mastani Sari.");
        sari.setPrice("2500");
        sari.setImageUrl("http://akrutisarees.com/wp-content/uploads/2016/07/online_embroidery_sarees_muk_1019.jpg");
        realm.insertOrUpdate(sari);

        sari.setId(5);
        sari.setTitle("Yashavanti Sari");
        sari.setDescription("This is the Description about Yashavanti Sari.");
        sari.setPrice("2500");
        sari.setImageUrl("http://akrutisarees.com/wp-content/uploads/2016/07/online_embroidery_sarees_muk_1018.jpg");
        realm.insertOrUpdate(sari);

        sari.setId(6);
        sari.setTitle("Devki Sari");
        sari.setDescription("This is a Description about Devki Sari.");
        sari.setPrice("2500");
        sari.setImageUrl("http://akrutisarees.com/wp-content/uploads/2016/07/online_embroidery_sarees_muk_1021.jpg");
        realm.insertOrUpdate(sari);
    }
    
    @Override
    public int hashCode() {
        return RealmInitialData.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof RealmInitialData;
    }
}
