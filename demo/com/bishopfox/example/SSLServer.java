package com.bishopfox.example;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

public class SSLServer implements Hello {

    public SSLServer() {}

    public String sayHello() { return "Remote Executed!"; }
    public String restart()  { return "Remote Executed!"; }
    public boolean login(String email, String password) { return true; }
    public String sayHello(String name, List test)  { return "Remote Executed!"; }
    public String sayNumber(int name)  { return "Remote Executed!"; }
    public String sayTest1(int name)  { return "Remote Executed!"; }
    public String sayTest2(byte name)  { return "Remote Executed!"; }
    public String sayTest3(short name)  { return "Remote Executed!"; }
    public String sayTest4(long name)  { return "Remote Executed!"; }
    public String sayTest5(char name)  { return "Remote Executed!"; }
    public String sayTest6(boolean name)  { return "Remote Executed!"; }
    public String sayTest7(float name)  { return "Remote Executed!"; }
    public String sayTest8(double name)  { return "Remote Executed!"; }
    public String sayTest9(Map name)  { return "Remote Executed!"; }
    public String sayTest10(HashMap name)  { return "Remote Executed!"; }
    public String sayTest11(List name)  { return "Remote Executed!"; }
    public String sayTest12(Object name)  { return "Remote Executed!"; }
    public ArrayList<String> say2things(String name, int test)  { return new ArrayList<String>(); }

    public static void main(String args[]) {
                // Create and install a security manager
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            //Registry registry = LocateRegistry.getRegistry();

            // Create SSL-based registry
            Registry registry = LocateRegistry.createRegistry(1099,
                new SslRMIClientSocketFactory(),
                new SslRMIServerSocketFactory());
            registry.bind("thisisatest", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
