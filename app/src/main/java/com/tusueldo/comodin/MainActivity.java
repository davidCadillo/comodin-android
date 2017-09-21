package com.tusueldo.comodin;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tusueldo.comodin.model.UserJson;
import com.tusueldo.comodin.utils.ComodinUtils;
import com.tusueldo.comodin.utils.SessionManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    private SessionManager sessionManager;
    private UserJson usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View parentView = navigationView.getHeaderView(0);
        sessionManager = SessionManager.getInstance();
        sessionManager.setActivity(this);
        HeaderViewHolder headerViewHolder = new HeaderViewHolder(parentView);
        headerViewHolder.fillDates();

    }


    @OnClick(R.id.fab)
    public void click(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            sessionManager.logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentoGenerico = null;
        int id = item.getItemId();
        if (id == R.id.nav_home) {
        } else if (id == R.id.nav_myaccount) {
            fragmentoGenerico = new CuentaFragment(getUser());
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_social_network) {
        }

        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public UserJson getUser() {
        if (usuario == null) {
            usuario = sessionManager.getUser();
        }
        return usuario;
    }

    private boolean checkUser() {
        return usuario != null;
    }

    class HeaderViewHolder {

        @BindView(R.id.img_user) ImageView img_user;
        @BindView(R.id.tv_nombre) TextView tv_nombre;
        @BindView(R.id.tv_email) TextView tv_email;

        HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        void fillDates() {
            UserJson user = getUser();
            if (checkUser()) {
                tv_email.setText(user.getEmail());
                if (user.getTipoUsuarioId() == 1 && user.getRuc() == null) {
                    tv_nombre.setText(ComodinUtils.toUpperWord(user.getNombresyapellidos()));
                } else {
                    tv_nombre.setText(user.getNombreComercial());
                }
            }
        }
    }

}
