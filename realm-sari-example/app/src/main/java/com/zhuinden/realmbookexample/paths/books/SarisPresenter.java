package com.zhuinden.realmbookexample.paths.books;

import android.content.Context;

import com.zhuinden.realmbookexample.application.RealmManager;
import com.zhuinden.realmbookexample.data.entity.SariFields;
import com.zhuinden.realmbookexample.data.entity.Sari;


import io.realm.Realm;


public class SarisPresenter {
    public static SarisPresenter getService(Context context) {
        //noinspection ResourceType
        return (SarisPresenter) context.getSystemService(TAG);
    }

    public static final String TAG = "SarisPresenter";

    public interface ViewContract {
        void showAddSariDialog();

        void showMissingTitle();

        void showEditSariDialog(Sari sari);

        interface DialogContract {
            String getTitle();
            String getDescription();
            String getPrice();
            String getThumbnail();

            void bind(Sari sari);
        }
    }

    ViewContract viewContract;

    boolean isDialogShowing;

    boolean hasView() {
        return viewContract != null;
    }

    public void bindView(ViewContract viewContract) {
        this.viewContract = viewContract;
        if(isDialogShowing) {
            showAddDialog();
        }
    }

    public void unbindView() {
        this.viewContract = null;
    }

    public void showAddDialog() {
        if(hasView()) {
            isDialogShowing = true;
            viewContract.showAddSariDialog();
        }
    }

    public void dismissAddDialog() {
        isDialogShowing = false;
    }

    public void showEditDialog(Sari sari) {
        if(hasView()) {
            viewContract.showEditSariDialog(sari);
        }
    }

    public void saveSari(ViewContract.DialogContract dialogContract) {
        if(hasView()) {
            final String description = dialogContract.getDescription();
            final String title = dialogContract.getTitle();
            final String price = dialogContract.getPrice();
            final String thumbnail = dialogContract.getThumbnail();

            if("".equals(title.trim())) {
                viewContract.showMissingTitle();
            } else {
                Realm realm = RealmManager.getRealm();
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Sari sari = new Sari();
                        long id = 1;
                        if(realm.where(Sari.class).count() > 0) {
                            id = realm.where(Sari.class).max(SariFields.ID).longValue() + 1; // auto-increment id
                        }
                        sari.setId(id);
                        sari.setTitle(title);
                        sari.setDescription(description);
                        sari.setPrice(price);
                        sari.setImageUrl(thumbnail);
                        realm.insertOrUpdate(sari);
                    }
                });
            }
        }
    }

    public void deleteSariById(final long id) {
        Realm realm = RealmManager.getRealm();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Sari sari = realm.where(Sari.class).equalTo(SariFields.ID, id).findFirst();
                if(sari != null) {
                    sari.deleteFromRealm();
                }
            }
        });
    }

    public void editSari(final ViewContract.DialogContract dialogContract, final long id) {
        Realm realm = RealmManager.getRealm();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Sari sari = realm.where(Sari.class).equalTo(SariFields.ID, id).findFirst();
                if(sari != null) {
                    sari.setTitle(dialogContract.getTitle());
                    sari.setDescription(dialogContract.getDescription());
                    sari.setPrice(dialogContract.getPrice());
                    sari.setImageUrl(dialogContract.getThumbnail());
                }
            }
        });
    }
}
