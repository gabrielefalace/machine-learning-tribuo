package io.falace;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.tribuo.Model;
import org.tribuo.MutableDataset;
import org.tribuo.Trainer;
import org.tribuo.classification.Label;
import org.tribuo.classification.LabelFactory;
import org.tribuo.classification.evaluation.LabelEvaluator;
import org.tribuo.classification.sgd.linear.LogisticRegressionTrainer;
import org.tribuo.data.csv.CSVLoader;
import org.tribuo.evaluation.TrainTestSplitter;

import java.io.IOException;
import java.nio.file.Paths;

public class DataLoader {

    public ImmutablePair<MutableDataset, MutableDataset> loadData(String datasetPath) throws IOException {
        var labelFactory = new LabelFactory();
        var csvLoader = new CSVLoader<>(labelFactory);

        // dataset comes without headers, we add them
        var irisHeaders = new String[]{"sepalLength", "sepalWidth", "petalLength", "petalWidth", "species"};

        var irisesSource = csvLoader.loadDataSource(Paths.get(datasetPath),"species",irisHeaders);

        // training/test split 70/30 %
        var irisSplitter = new TrainTestSplitter<>(irisesSource,0.7,1L);

        var trainingDataset = new MutableDataset<>(irisSplitter.getTrain());

        var testingDataset = new MutableDataset<>(irisSplitter.getTest());

        System.out.println(String.format("Training data size = %d, number of features = %d, number of classes = %d",trainingDataset.size(),trainingDataset.getFeatureMap().size(),trainingDataset.getOutputInfo().size()));
        System.out.println(String.format("Testing data size = %d, number of features = %d, number of classes = %d",testingDataset.size(),testingDataset.getFeatureMap().size(),testingDataset.getOutputInfo().size()));

        return new ImmutablePair<>(trainingDataset, testingDataset);
    }

    public Model<Label> trainModel(MutableDataset trainingDataset) {
        Trainer<Label> trainer = new LogisticRegressionTrainer();
        System.out.println(trainer);
        Model<Label> irisModel = trainer.train(trainingDataset);
        return irisModel;
    }

    public void evaluateModel(Model<Label> model, MutableDataset testDataset) {
        var evaluator = new LabelEvaluator();
        var evaluation = evaluator.evaluate(model,testDataset);
        System.out.println(evaluation.toString());
    }

}
