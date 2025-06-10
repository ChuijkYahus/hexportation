package dev.kineticcat.hexportation.fabric.api.config;

import dev.kineticcat.hexportation.Hexportation;

/**
 * Platform-agnostic class for statically accessing current config values.
 * If any of the config types (common, client, server) are not needed in your mod,
 * feel free to remove anything related to them in this class and platform-specific config implementations.
 */
public class ComplexhexConfig {
    private static final CommonConfigAccess dummyCommon = new CommonConfigAccess() {
    };
    private static final ClientConfigAccess dummyClient = new ClientConfigAccess() {
    };
    private static final ServerConfigAccess dummyServer = new ServerConfigAccess() {
    };
    private static CommonConfigAccess common = dummyCommon;
    private static ClientConfigAccess client = dummyClient;
    private static ServerConfigAccess server = dummyServer;

    public static CommonConfigAccess getCommon() {
        return common;
    }

    public static void setCommon(CommonConfigAccess common) {
        if (ComplexhexConfig.common != dummyCommon) {
            Hexportation.LOGGER.warn("CommonConfigAccess was replaced! Old {} New {}", ComplexhexConfig.common.getClass().getName(), common.getClass().getName());
        }
        ComplexhexConfig.common = common;
    }

    public static ClientConfigAccess getClient() {
        return client;
    }

    public static void setClient(ClientConfigAccess client) {
        if (ComplexhexConfig.client != dummyClient) {
            Hexportation.LOGGER.warn("ClientConfigAccess was replaced! Old {} New {}", ComplexhexConfig.client.getClass().getName(), client.getClass().getName());
        }
        ComplexhexConfig.client = client;
    }

    public static ServerConfigAccess getServer() {
        return server;
    }

    public static void setServer(ServerConfigAccess server) {

        if (ComplexhexConfig.server != dummyServer) {
            Hexportation.LOGGER.warn("ServerConfigAccess was replaced! Old {} New {}", ComplexhexConfig.server.getClass().getName(), server.getClass().getName());
        }
        ComplexhexConfig.server = server;
    }

    public static int bound(int toBind, int lower, int upper) {
        return Math.min(Math.max(toBind, lower), upper);
    }

    public static double bound(double toBind, double lower, double upper) {
        return Math.min(Math.max(toBind, lower), upper);
    }

    public interface CommonConfigAccess {
    }

    public interface ClientConfigAccess {
    }

    public interface ServerConfigAccess {
    }
}
