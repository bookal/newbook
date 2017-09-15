package org.androidtown.bookalarm;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class UserListAdapter extends BaseAdapter{
    private Context context;
    private List<User> userList;
    private Activity parentActivity;
    private List<User> saveList;

    public UserListAdapter(Context context, List<User>userList,Activity parentActivity,List<User>saveList){
        this.context =context;
        this.userList=userList;
        this.parentActivity=parentActivity;
        this.saveList = saveList;
    }
    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.user,null); //한 명의 사용자에 대한 뷰
        final TextView userID =(TextView)v.findViewById(R.id.userID);
        TextView userPassword =(TextView)v.findViewById(R.id.userPassword);
        TextView userName =(TextView)v.findViewById(R.id.userName);
        TextView userAge =(TextView)v.findViewById(R.id.userAge);

        userID.setText(userList.get(i).getUserID());
        userPassword.setText(userList.get(i).getUserPassword());
        userName.setText(userList.get(i).getUserName());
        userAge.setText(userList.get(i).getUserAge());

        v.setTag(userList.get(i).getUserID());

        Button deleteButton = (Button)v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                userList.remove(i);
                                for(int i=0; i<saveList.size(); i++){
                                    if(saveList.get(i).getUserID().equals(userID.getText().toString())){
                                        saveList.remove(i);
                                        break;
                                    }
                                }

                                notifyDataSetChanged();
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                DeleteRequest deleteRequest = new DeleteRequest(userID.getText().toString(),responseListener);
                RequestQueue queue = Volley.newRequestQueue(parentActivity);
                queue.add(deleteRequest);
            }
        });
        return v;
    }
}
