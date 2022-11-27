package io.falace;

import java.io.IOException;

public class Main {

    static DataLoader dataLoader = new DataLoader();

    public static void main(String[] args) throws IOException {
        var datasets = dataLoader.loadData("bezdekIris.data");
        var model = dataLoader.trainModel(datasets.left);
        dataLoader.evaluateModel(model, datasets.right);
    }
}