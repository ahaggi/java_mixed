package base.util.database;
wpackage base.util.database;
import base.Main;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import org.json.JSONObject;

import java.sql.*;
import java.util.Properties;

/**
 * Created by sindre on 07/10/16.
 */
public class Database {
    private String url = "jdbc:postgresql://localhost:5432/myGuild";
    private Properties props = new Properties();
    private Connection conn;



    public void initConnection(){
        props.setProperty("user","useruseruseruser");
        props.setProperty("password","passwordpasswordpassword");
        props.setProperty("SSL","false");
        try {
            conn = DriverManager.getConnection(url, props);
            if(conn!= null)System.out.println("Connected");
            else System.out.println("not connected");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("not connected");
        }

    }

    public void startConnection(){
        try {

            initConnection();
            if (conn!=null) System.out.println("Connected");
            else System.out.println("nope, sry");


        } catch (Exception ee){
            ee.printStackTrace();
        }
    }
    public boolean checkForUser (MessageReceivedEvent event) {
        try {
            PreparedStatement read_statement;
            ResultSet results;
            Integer value = 0;
            Boolean isInDatabase = false;
            read_statement = conn.prepareStatement("SELECT COUNT(userid) FROM guild_members WHERE userid ='" + event.getAuthor().getId() + "' ;");
            results = read_statement.executeQuery();
            while (results.next()) {
                value = results.getInt(1);
            }
            if (value == 1) isInDatabase = true;
            read_statement.close();
            return isInDatabase;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkForServer (MessageReceivedEvent event) {
        try {
            PreparedStatement read_statement;
            ResultSet results;
            Integer value = 0;
            Boolean isInDatabase = false;
            read_statement= conn.prepareStatement("SELECT COUNT(guild_serverid) FROM guild_ids WHERE guild_serverid ='" + event.getGuild().getId() + "' ;");
            results = read_statement.executeQuery();
            while (results.next()) {
                value = results.getInt(1);
            }
            if (value == 1) isInDatabase = true;
            read_statement.close();
            return isInDatabase;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Integer fetchMemberId (MessageReceivedEvent event) {
        try {
            PreparedStatement read_statement;
            ResultSet results;
            Integer value = 0;
            read_statement = conn.prepareStatement("SELECT memberid FROM guild_members WHERE userid ='" + event.getAuthor().getId() + "';");
            results = read_statement.executeQuery();
            while (results.next()) {
                value = (results.getInt(1));
            }
            read_statement.close();
            return value;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Integer fetchMemberIdByUserid (String id) {
        PreparedStatement read_statement;
        ResultSet results;
        Integer returnValue =null;
        try {
            read_statement = conn.prepareStatement("SELECT memberid FROM guild_members WHERE userid =?");
            read_statement.setString(1, id);
            results = read_statement.executeQuery();
            while (results.next()) {
                returnValue = results.getInt(1);
            }
            read_statement.close();
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Could not fetch memberid");
        return returnValue;
    }
    public void addServer(MessageReceivedEvent event) {
        try {
            PreparedStatement insertServerRegistrationStatement;
            insertServerRegistrationStatement = conn.prepareStatement("INSERT INTO public.guild_ids (guild_name, guild_serverid) VALUES (?, ?)");
            insertServerRegistrationStatement.setString(1, event.getGuild().getName());
            insertServerRegistrationStatement.setString(2, event.getGuild().getId());
            insertServerRegistrationStatement.execute();
            System.out.println("Server Registered!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDataToDatabase(String name, MessageReceivedEvent event) {
        try {
            PreparedStatement insertRegistrationStatement;
            PreparedStatement insertRegistrationAttributesStatement;
            PreparedStatement insertRegistrationEquipmentStatement;
            PreparedStatement insertRegistrationTechniquesStatement;
            insertRegistrationStatement = conn.prepareStatement("INSERT INTO public.Guild_Members (name, charlv, userid, exp, gender, admin) VALUES (?, 1, ?, 0, 0, false)");
            insertRegistrationAttributesStatement = conn.prepareStatement("INSERT INTO public.attributes (memberid, strength, constitution, endurance, intelligence, magic_affinity, dexterity, willpower, perception, speed, availableattributes) VALUES ((SELECT memberid FROM guild_members where name = ?),5, 5, 5, 5, 5, 5, 5, 5, 5, 0)");
            insertRegistrationEquipmentStatement = conn.prepareStatement("INSERT INTO player_equipment (memberid, lefthand, righthand, playerboots, playergauntlet, playerheadwear, playerlegging, playertorso) VALUES (?,600001,700001,100001,200001,300001,400001,500001)");
            insertRegistrationTechniquesStatement = conn.prepareStatement("INSERT INTO player_techniques (memberid) VALUES (?)");
            insertRegistrationStatement.setString(1, name);
            insertRegistrationStatement.setString(2, event.getAuthor().getId());
            insertRegistrationStatement.executeUpdate();
            insertRegistrationAttributesStatement.setString(1, name);
            insertRegistrationAttributesStatement.execute();
            insertRegistrationEquipmentStatement.setInt(1, fetchMemberId(event));
            insertRegistrationEquipmentStatement.execute();
            insertRegistrationTechniquesStatement.setInt(1, fetchMemberId(event));
            insertRegistrationTechniquesStatement.execute();
            event.getTextChannel().sendMessage("Registered!");


        } catch (SQLException e) {
            e.printStackTrace();
            event.getTextChannel().sendMessage("sorry, that username is taken");
        }
    }
    public String[] getUsers(){
        try {
            PreparedStatement read_statement;
            ResultSet results;
            int Length = 0;
            read_statement = conn.prepareStatement("SELECT COUNT (userid) FROM guild_members");
            results = read_statement.executeQuery();
            while (results.next()) {
                Length = results.getInt(1);
            }
            String[] users = new String[Length];
            read_statement = conn.prepareStatement("SELECT userid FROM guild_members");
            results = read_statement.executeQuery();
            int i = 0;
            while (results.next()) {
                users[i] = results.getString(1);
                i++;
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Could not fetch users");
        return null;
    }

    public Integer readData(String aQuery,int aMemberid) {
        try {
            PreparedStatement read_statement;
            ResultSet results;
            Integer value = 0;
            read_statement = conn.prepareStatement(aQuery);
            read_statement.setInt(1, aMemberid);
            results = read_statement.executeQuery();

            while (results.next()) {
                value = (results.getInt(1));
            }
            read_statement.close();
            return value;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Failed to fetch");
        return 0;
    }
    public Integer[] readAttributeArray(String aQuery, int aMemberid) {
        try {
            PreparedStatement read_statement;
            ResultSet results;
            Integer value[] = new Integer[9];
            read_statement = conn.prepareStatement(aQuery);
            read_statement.setInt(1, aMemberid);
            results = read_statement.executeQuery();
            while (results.next()) {
                for(int i = 0; i < 9; i++) {
                    value[i] = (results.getInt(i+1));
                }
            }
            read_statement.close();
            return value;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Failed to fetch");
        return null;
    }
    public Integer[] readEquipedItemArray(String aQuery, int aMemberid, Integer numberOfThingsToFetch) {
        try {
            PreparedStatement read_statement;
            ResultSet results;
            Integer value[] = new Integer[numberOfThingsToFetch];
            read_statement = conn.prepareStatement(aQuery);
            read_statement.setInt(1, aMemberid);
            results = read_statement.executeQuery();
            while (results.next()) {
                for(int i = 0; i < numberOfThingsToFetch; i++) {
                    value[i] = (results.getInt(i+1));
                }
            }
            read_statement.close();
            return value;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Failed to fetch");
        return null;
    }
    public void updateAttributes(String aQuery, int aMemberid, int availablePoints, MessageReceivedEvent event) {
        try {
            PreparedStatement update_statement;
            update_statement = conn.prepareStatement(aQuery);
            update_statement.setInt(1, aMemberid);
            update_statement.execute();
            update_statement.close();
            String updateQuery = "UPDATE attributes SET availableattributes=" + availablePoints + " WHERE memberid =?";
            update_statement = conn.prepareStatement(updateQuery);
            update_statement.setInt(1, aMemberid);
            update_statement.execute();
            update_statement.close();
            System.out.println("Updated");
            event.getTextChannel().sendMessage("Attributes added");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer readAvailableAttributes(int aMemberId) {
        try {
            PreparedStatement read_statement;
            ResultSet results;
            Integer value = 0;
            read_statement = conn.prepareStatement("SELECT availableattributes FROM attributes WHERE memberid =?");
            read_statement.setInt(1, aMemberId);
            results = read_statement.executeQuery();
            while (results.next()) {
                value = results.getInt(1);
            }
            read_statement.close();
            return value;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean commitTimerExp(Integer[] playerIds, Integer exp) {
        try {
            Statement executeBatch;
            conn.setAutoCommit(false);
            executeBatch = conn.createStatement();

            for(int i = 0; i < playerIds.length; i++) {
                String query = "UPDATE guild_members SET exp= exp +" + exp + " WHERE memberid =" + playerIds[i] + ";";
                executeBatch.addBatch(query);
            }
            executeBatch.executeBatch();
            conn.setAutoCommit(true);
            expChecker();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void commitExp(Integer[] playerIds, Integer[] exp) {
        try {
            Statement executeBatch;
            conn.setAutoCommit(false);
            executeBatch = conn.createStatement();

            for(int i = 0; i < playerIds.length; i++) {
                String query = "UPDATE guild_members SET exp= exp +" + exp[i] + " WHERE memberid =" + playerIds[i] + ";";
                executeBatch.addBatch(query);
            }
            executeBatch.executeBatch();
            conn.setAutoCommit(true);
            expChecker();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void expChecker () {
        try {
            PreparedStatement read_statement = conn.prepareStatement("SELECT COUNT(memberid) FROM guild_members");
            ResultSet results = read_statement.executeQuery();
            Integer length = 0;
            while (results.next()){
                length = results.getInt(1);
            }

            Double[] experience = new  Double[length];
            Integer[] level = new Integer[length];
            String[] names = new String[length];
            String[] leveleupPlayerIds = new String[length];
            Integer[] levelupLevel = new Integer[length];
            String[] userid = new String[length];
            int i = 0;
            int k = 0;

            read_statement.close();

            read_statement = conn.prepareStatement("SELECT exp, charlv, name, userid  FROM guild_members");
            results = read_statement.executeQuery();
            while (results.next()){
                experience[i] = Double.valueOf(results.getInt(1));
                level[i] = results.getInt(2);
                names[i] = results.getString(3);
                userid[i] = results.getString(4);
                i++;
            }
            read_statement.close();

            for (i = 0; i < length; i++){
                double x = experience[i];
                Double  sqrt = Math.sqrt((100*(2*x+25)));
                Double deservedlvl = (sqrt+50)/100;
                Integer test = deservedlvl.intValue();
                if (test > level[i]) {
                    System.out.println("Congratualtion to " + names[i] + " for reaching lvl " + test + "!");
                    Main.jda.getUserById(userid[i]).getPrivateChannel().sendMessage("Congratulation! You leveled up!");
                    Main.jda.getUserById(userid[i]).getPrivateChannel().sendMessage("You are now level " + test + " and have been given " + ((test-level[i])*5) + " attributepoints to assign");
                    leveleupPlayerIds[k] = userid[i];
                    levelupLevel[k] = test;
                    k++;
                }
            }
            if(leveleupPlayerIds[0] != null){
                batchLevelUp(leveleupPlayerIds, levelupLevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void batchLevelUp(String[] playerIds, Integer[] level) {
        try {
            ResultSet results;
            PreparedStatement read_statement = conn.prepareStatement("SELECT userid, memberid, charlv FROM guild_members");
            results = read_statement.executeQuery();
            String value;
            int length = 0;
            for (int i = 0; i < playerIds.length; i ++) {
                if (playerIds[i] != null){
                    length++;
                }
            }
            Integer[] charlv = new Integer[length];
            Integer[] memberids = new Integer[length];
            while (results.next()) {
                value = results.getString(1);
                for (int j = 0; j < length; j++) {
                    if (value.equals(playerIds[j])) {
                        memberids[j] = results.getInt(2);
                        charlv[j] = results.getInt(3);
                        j = length;
                    }
                }
            }
            read_statement.close();
            Statement executeUpdateBatch = conn.createStatement();
            conn.setAutoCommit(false);
            for (int i = 0; i < length; i++) {
                Integer value1 = level[i];
                Integer value2 = charlv[i];
                Integer avaiableAttributesAdd = (value1-value2) *5;
                executeUpdateBatch.addBatch("UPDATE guild_members SET charlv = " + level[i] + " WHERE memberid =" + memberids[i] + ";");
                executeUpdateBatch.addBatch("UPDATE attributes SET availableattributes = availableattributes + " + avaiableAttributesAdd + " WHERE memberid =" + memberids[i] + ";");
            }
            executeUpdateBatch.executeBatch();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] getServers() {
        try {
            PreparedStatement read_statement;
            ResultSet results;
            int length = 0;
            int i = 0;
            String[] servers;
            read_statement = conn.prepareStatement("SELECT  COUNT (guild_id) FROM guild_ids");
            results = read_statement.executeQuery();
            while (results.next()) {
                length = results.getInt(1);
            }
            servers = new String[length];
            read_statement = conn.prepareStatement("SELECT guild_serverid FROM guild_ids");
            results = read_statement.executeQuery();
            while (results.next()) {
                servers[i] = results.getString(1);
                i++;
            }
            return servers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("could not fetch servers");
        return null;
    }
    public String[] getPlayerInventory(Integer memberid) {
        try {
            ResultSet results;
            PreparedStatement read_statement;
            String[] jsonString = new String[2];
            read_statement = conn.prepareStatement("SELECT consumablesinventory, equipmentinventory FROM player_equipment WHERE memberid=" + memberid + ";");
            results = read_statement.executeQuery();
            while (results.next()){
                jsonString[0] = results.getString(1);
                jsonString[1] = results.getString(2);
            }
            read_statement.close();
            return jsonString;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[][] getInventories (Integer[] memberids) {
        try {
            ResultSet results;
            PreparedStatement read_statement;
            int memberIdLength = memberids.length;

            String jsonString[][] = new String[2][memberIdLength];
            read_statement = conn.prepareStatement("SELECT memberid, consumablesinventory, equipmentinventory FROM player_equipment");
            results = read_statement.executeQuery();
            while (results.next()) {
                for (int i = 0; i < memberIdLength; i++) {
                    if (results.getInt(1) ==memberids[i]) {
                        jsonString[0][i] = results.getString(2);
                        jsonString[1][i] = results.getString(3);
                    }
                }
            }
            read_statement.close();
            return jsonString;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Failed to fetch items");
        return null;
    }
    public void setBackstory (String text, Integer memberid) {
        try {
            PreparedStatement updateStatement = conn.prepareStatement("UPDATE guild_members SET backstory = '" + text + "' WHERE memberid = ?;");
            updateStatement.setInt(1, memberid);
            updateStatement.execute();
            System.out.println("Added backstory");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getBackstory (Integer memberid) {
        String story = null;
        try {
            PreparedStatement read_statement = conn.prepareStatement("SELECT backstory FROM guild_members WHERE memberid = ?;");
            read_statement.setInt(1, memberid);
            ResultSet results = read_statement.executeQuery();
            while (results.next()) {
                story = results.getString(1);
            }
            return story;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return story;
    }
    public String[] getBackstories(Integer memberid[]) {
        String[] stories = new String[memberid.length];
        try {
            PreparedStatement read_statement = conn.prepareStatement("SELECT memberid, backstory FROM guild_members;");
            ResultSet results = read_statement.executeQuery();
            while (results.next()){
                for (int i = 0; i < memberid.length; i++){
                    if (memberid[i] == results.getInt(1)){
                        stories[i] = results.getString(2);
                        i = memberid.length;
                    }
                }
            }
            return stories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stories;
    }
    public void setGender (Integer gender, Integer memberid) {
        try {
            if (gender < 3 && 0 < gender) {
                PreparedStatement updateStatement = conn.prepareStatement("UPDATE guild_members SET gender = " + gender + " WHERE memberid = ?;");
                updateStatement.setInt(1, memberid);
                updateStatement.execute();
                System.out.println("Assigned gender");
            } else {
                System.out.println("Not a valid gender");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getGender (Integer memberid) {
        Integer gender = 0;
        try {
            PreparedStatement read_statement = conn.prepareStatement("SELECT gender FROM guild_members WHERE memberid = ?;");
            read_statement.setInt(1, memberid);
            ResultSet results = read_statement.executeQuery();
            while (results.next()){
                gender = results.getInt(1);
            }
            return gender;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gender;
    }
    public Integer[] getGenders (Integer[] memberid){
        Integer[] genders = new Integer[memberid.length];
        try {
            PreparedStatement read_statement = conn.prepareStatement("SELECT memberid, gender FROM guild_members;");
            ResultSet results = read_statement.executeQuery();
            while (results.next()){
                for (int i = 0; i < memberid.length; i++){
                    if (memberid[i] == results.getInt(1)) {
                        genders[i] = results.getInt(2);
                        i = memberid.length;
                    }
                }
            }
            return genders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genders;
    }
    public boolean hasSetGender (Integer memberid) {
        try {
            PreparedStatement read_statement = conn.prepareStatement("SELECT gender FROM guild_members WHERE memberid = ?;");
            read_statement.setInt(1, memberid);
            ResultSet results = read_statement.executeQuery();
            while (results.next()){
                if (results.getInt(1) != 0) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isAdmin (Integer memberid) {
        try {
            PreparedStatement read_statement = conn.prepareStatement("SELECT admin FROM guild_members WHERE memberid = ?");
            read_statement.setInt(1, memberid);
            ResultSet results = read_statement.executeQuery();
            while (results.next()) {
                if (results.getBoolean(1) == true) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String[][] getTechniques(Integer[] memberids) {
        try {
            ResultSet results;
            PreparedStatement read_statement;
            int memberIdLength = memberids.length;

            String jsonString[][] = new String[2][memberIdLength];
            read_statement = conn.prepareStatement("SELECT memberid, physicaltechniques, spells FROM player_techniques");
            results = read_statement.executeQuery();
            while (results.next()) {
                for (int i = 0; i < memberIdLength; i++) {
                    if (results.getInt(1) == memberids[i]) {
                        jsonString[0][i] = results.getString(2);
                        jsonString[1][i] = results.getString(3);
                    }
                }
            }
            read_statement.close();
            return jsonString;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Failed to fetch techniques");
        return null;
    }
    public String[] getPlayerTechniques(Integer memberid) {
        try {
            ResultSet results;
            PreparedStatement read_statement;
            String[] jsonString = new String[2];
            read_statement = conn.prepareStatement("SELECT physicaltechniques, spells FROM player_techniques WHERE memberid=" + memberid + ";");
            results = read_statement.executeQuery();
            while (results.next()){
                jsonString[0] = results.getString(1);
                jsonString[1] = results.getString(2);
            }
            read_statement.close();
            return jsonString;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
