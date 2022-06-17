package com.projects.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.projects.atmconsultoria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarEmail();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_servicos, R.id.nav_clientes, R.id.nav_sobre, R.id.nav_contato)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void enviarEmail() {
        String celularr = "tel:11996352894";
        String imagem = "https://www.google.com/search?q=imagen&source=lnms&tbm=isch&sa=X&ved=2ahUKEwixsNTBh7X4AhXZpZUCHQH9Ai4Q_AUoAXoECAEQAw&biw=1920&bih=932&dpr=1#imgrc=lhKJIkqqBgBSmM";
        String endereco = "https://www.google.com/maps/place/Morro+Santos+da+Silva/@-23.2816154,-46.7451078,13z/data=!4m9!1m2!2m1!1sparque!3m5!1s0x94cee5f344c9eddd:0x3dab380264b57a23!8m2!3d-23.3177342!4d-46.7157353!15sCgZwYXJxdWWSAQ9lY29sb2dpY2FsX3Bhcms?hl=pt-BR";
        //Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(endereco));

        Intent intent = new Intent( Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"atendimento@atmconsultoria.com.br"});
        intent.putExtra(Intent.EXTRA_SUBJECT, new String[]{"Contato pelo App"});
        intent.putExtra(Intent.EXTRA_TEXT, new String[]{"Mensagem automatica"});

        // https://www.sitepoint.com/mime-types-complete-list/
//        intent.setType("message/rfc822");
//        intent.setType("text/plain");
        intent.setType("image/*");

        startActivity( Intent.createChooser(intent,"Compartilhar"));
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}