package com.paint100;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;


/**
 * Class that controls the creation and upload to a local server
 */
public class ImageUploadServer {

    /**
     * Class that controls the creation and upload to a local server
     */
    public ImageUploadServer() {}

    private static File currentImage = new File("uploaded_image.jpg");  // Image file path
    private static final File defaultImage = new File("default_image.jpg");  // Default blank image path

    public static void main(String[] args) throws Exception {
        // At server startup, reset to default image (or create a blank one)
        resetToDefaultImage();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new ImageHandler());   // Serve images
        server.createContext("/upload", new UploadHandler()); // Upload images
        server.setExecutor(null);  // Default executor
        server.start();
        System.out.println("Server started at http://localhost:8080");
    }

    // Reset the current image to a default image (or blank image)
    private static void resetToDefaultImage() throws IOException {
        currentImage.delete();

    }


    private static class ImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                // Serve the current image
                if (currentImage.exists()) {
                    String mimeType = Files.probeContentType(currentImage.toPath());
                    exchange.getResponseHeaders().set("Content-Type", mimeType);
                    exchange.sendResponseHeaders(200, currentImage.length());

                    try (FileInputStream fis = new FileInputStream(currentImage);
                         OutputStream os = exchange.getResponseBody()) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            os.write(buffer, 0, bytesRead);
                        }
                    }
                } else {
                    String response = "No image found.";
                    exchange.sendResponseHeaders(404, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else {
                exchange.sendResponseHeaders(405, -1);  // Method not allowed
            }
        }
    }

    // UploadHandler to handle image uploads
    private static class UploadHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                FileOutputStream fos = new FileOutputStream(currentImage);  // Overwrite the current image

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                fos.close();
                is.close();

                String response = "Image uploaded";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);  // Method not allowed
            }
        }
    }
}
