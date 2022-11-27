package io.falace;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        var datasets = DataLoader.loadData("bezdekIris.data");
        var model = ModelTrainer.trainModel(datasets.left);
        ModelTester.evaluateModel(model, datasets.right);
    }
}