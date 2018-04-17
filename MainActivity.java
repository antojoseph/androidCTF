package com.example.antojoseph.decryptor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            String result = decrypt("J0yGSBs5EaApkR67G/iZjK12kkTk1XBMzWdy7P58iqGUDfLjLlGOZf/nryFXQqBh");
            Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
        } catch ( Exception e){
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }

    }




    public String decrypt(String ciphertext) throws Exception {
         Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
         SecretKeySpec key;
         byte[] salt = "SampleSalt".getBytes();
         key = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec("I</3Porgis!".toCharArray(), salt, 1000, 128)).getEncoded(), "AES");

         byte[] ciphertextBytes = Base64.decode(ciphertext.getBytes(), 2);
         Log.d("Status", ciphertextBytes.toString());
         cipher.init(2, key);
        return new String(cipher.doFinal(ciphertextBytes), "UTF-8");
    }
}
