package in.example.oopsrestaurant;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.auth.User;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class Utils {
    public static String extDirectoryName = "Hogar";
    public static String CHANNEL_UPLOAD_IMG_ID = "IMG_UPlOAD", CHANNEL_UPLOAD_NAME="Image Uploads";
//    public static void logOut(Context context) {
//        FirebaseAuth.getInstance().signOut();
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        realm.deleteAll();
//        realm.commitTransaction();
//        context.getSharedPreferences("MyPrefs", MODE_PRIVATE).edit().clear().apply();
//        Intent intent = new Intent(context, LoginActivityTest.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(intent);
//    }

//    public static void storeUserInfo(User user, Context context) {
//        String json = new Gson().toJson(user);
//        context.getSharedPreferences("MyPrefs", MODE_PRIVATE).edit().putString("loggedInUser", json).apply();
//    }
//
//    public static User fetchUserInfo(Context context) {
//        String userJson = context.getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("loggedInUser", "");
//        User user = null;
//        try {
//            user = new Gson().fromJson(userJson, User.class);
//        } catch (Exception e) {
//        }
//        return user;
//    }
//
//    public static String formatName(String name){
//        name = " " + name.trim();
//        String name1 ="";
//        for(int i=1;i<name.length();i++)
//        {
//            if(name.charAt(i)!= ' ' && name.charAt(i-1)==' ')
//                name1 += Character.toUpperCase(name.charAt(i));
//            else if(name.charAt(i)== ' ' && name.charAt(i-1)==' ' ){
//                continue;
//            }
//            else name1 += name.charAt(i);
//        }
//        return name1;
//    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
