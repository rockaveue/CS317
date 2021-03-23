package com.example.biedaalt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.getstream.sdk.chat.ChatUI;
import com.getstream.sdk.chat.view.channels.ChannelsView;
import com.getstream.sdk.chat.viewmodel.channels.ChannelsViewModel;
import com.getstream.sdk.chat.viewmodel.channels.ChannelsViewModelBinding;
import com.getstream.sdk.chat.viewmodel.factory.ChannelsViewModelFactory;

import org.jetbrains.annotations.Nullable;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.client.utils.FilterObject;
import io.getstream.chat.android.livedata.ChatDomain;
import kotlin.Unit;

import static java.util.Collections.singletonList;

public final class MainActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step 1 - Set up the client for API calls, the domain for offline storage and the UI components
        ChatClient client = new ChatClient.Builder("rhgx352ga5gc", getApplicationContext()).build();
        ChatDomain domain = new ChatDomain.Builder(client, getApplicationContext()).build();
        new ChatUI.Builder(client, domain, getApplicationContext()).build();

        // Step 2 - Authenticate and connect the user
        User user = new User();
        user.setId("summer-brook-2");
        user.getExtraData().put("name", "Paranoid Android");
        user.getExtraData().put("image", "https://bit.ly/2TIt8NR");

        client.setUser(
                user,
                "ukg52d4azdkq5eked6p2zxazppgnyxngb2gh5kf3v5xgv8e5jtrn8qm3vda245jw",
                null
        );

        // Step 3 - Set the channel list filter and order
        // This can be read as requiring only channels whose "type" is "messaging" AND
        // whose "members" include our "user.id"
        FilterObject filter = Filters.and(
                Filters.eq("type", "messaging"),
                Filters.in("members", singletonList(user.getId()))
        );
        ChannelsViewModelFactory factory = new ChannelsViewModelFactory(
                filter,
                ChannelsViewModel.DEFAULT_SORT
        );
        ChannelsViewModel channelsViewModel = new ViewModelProvider(this, factory).get(ChannelsViewModel.class);

        // Step 4 - Connect the ChannelsViewModel to the ChannelsView, loose coupling makes it easy to customize
        ChannelsView channelsView = findViewById(R.id.channelsView);
        ChannelsViewModelBinding.bind(channelsViewModel, channelsView, this);
        channelsView.setOnChannelClickListener((channel -> {
            startActivity(ChannelActivity.newIntent(this, channel));
            return Unit.INSTANCE;
        }));
    }
}