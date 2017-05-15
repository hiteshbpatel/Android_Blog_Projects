package com.zhuinden.realmbookexample.paths.books;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhuinden.realmbookexample.R;
import com.zhuinden.realmbookexample.application.RealmManager;
import com.zhuinden.realmbookexample.data.entity.Sari;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class SarisActivity
        extends AppCompatActivity
        implements SarisPresenter.ViewContract {

    @BindView(R.id.main_root)
    ViewGroup root;

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Realm realm;

    SarisPresenter sarisPresenter;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RealmManager.initializeRealmConfig(getApplicationContext());
        super.onCreate(savedInstanceState);
        SarisScopeListener fragment = (SarisScopeListener) getSupportFragmentManager().findFragmentByTag("SCOPE_LISTENER");
        if(fragment == null) {
            fragment = new SarisScopeListener();
            getSupportFragmentManager().beginTransaction().add(fragment, "SCOPE_LISTENER").commit();
        }
        //get realm instance
        realm = RealmManager.getRealm();

        //get presenter instance
        sarisPresenter = fragment.getPresenter();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //set toolbar
        setSupportActionBar(toolbar);

        //setup recycler
        recycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        // get all persisted objects
        // changes will be reflected automatically
        recycler.setAdapter(new SarisAdapter(this, realm.where(Sari.class).findAllAsync()));

        if(savedInstanceState == null) {
            Toast.makeText(this, R.string.press_to_edit_long_press_remove, Toast.LENGTH_LONG).show();
        }

        // bind to presenter
        sarisPresenter.bindView(this);
    }

    @Override
    protected void onDestroy() {
        if(sarisPresenter != null) {
            sarisPresenter.unbindView();
        }
        if(dialog != null) {
            dialog.dismiss();
        }
        super.onDestroy();
    }

    @OnClick(R.id.fab)
    void onFabClicked() {
        sarisPresenter.showAddDialog();
    }

    @Override
    public void showAddSariDialog() {
        final View content = getLayoutInflater().inflate(R.layout.edit_item, root, false);
        final DialogContract dialogContract = (DialogContract) content;

        AlertDialog.Builder builder = new AlertDialog.Builder(SarisActivity.this);
        builder.setView(content)
                .setTitle(getString(R.string.add_sari))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sarisPresenter.saveSari(dialogContract);
                        sarisPresenter.dismissAddDialog();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sarisPresenter.dismissAddDialog();
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showMissingTitle() {
        Toast.makeText(SarisActivity.this, getString(R.string.entry_not_saved), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEditSariDialog(Sari sari) {
        final View content = getLayoutInflater().inflate(R.layout.edit_item, root, false);
        final SarisPresenter.ViewContract.DialogContract dialogContract = (SarisPresenter.ViewContract.DialogContract) content;
        dialogContract.bind(sari);

        final long id = sari.getId();

        AlertDialog.Builder builder = new AlertDialog.Builder(SarisActivity.this);
        builder.setView(content)
                .setTitle(getString(R.string.edit_sari))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sarisPresenter.editSari(dialogContract, id);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public Object getSystemService(String name) {
        if(name.equals(SarisPresenter.TAG)) {
            return sarisPresenter;
        }
        return super.getSystemService(name);
    }
}
