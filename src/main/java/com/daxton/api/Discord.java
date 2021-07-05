package com.daxton.api;

import com.daxton.Main;
import com.daxton.page.main.ServerMenuPage;
import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.discordjson.json.ChannelModifyRequest;
import discord4j.rest.entity.RestChannel;

public class Discord {

    public static GatewayDiscordClient gateway;

    public static DiscordClient client;

    public static long channelID;

    public static boolean enable = false;

    public Discord(){

    }

    public Discord(String token, long channelID, boolean enable){
        Discord.enable = enable;
        Discord.channelID = channelID;
        client = DiscordClient.create(token);
        gateway = DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();

        //getMessage();


        //gateway.onDisconnect().block();
    }

    public void sendMessage(String message){
        if(enable){
            TextChannel channel = (TextChannel) gateway.getChannelById(Snowflake.of(channelID)).block();

            if (channel != null) {
                channel.createMessage(message).block();

            }
        }

    }

    public static void setChannelName(String name){
        Channel channel = gateway.getChannelById(Snowflake.of(channelID)).block();
        RestChannel restChannel = channel.getRestChannel();

        restChannel.modify(ChannelModifyRequest.builder().name(name).build(), null).block();
    }

    public void getMessage(){
        gateway.on(MessageCreateEvent.class).subscribe(event -> {

            final Message message = event.getMessage();

            ServerMenuPage.print(message.getContent());
//            if ("!ping".equals(message.getContent())) {
//                final MessageChannel channel = message.getChannel().block();
//                channel.createMessage("Pong!").block();
//            }
        });
    }

    public static void send(String message){
        if(Main.discord != null){
            Main.discord.sendMessage(message);
        }
    }


}
