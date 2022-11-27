package io.falace;

import org.tribuo.Model;
import org.tribuo.MutableDataset;
import org.tribuo.Trainer;
import org.tribuo.classification.Label;
import org.tribuo.classification.sgd.linear.LogisticRegressionTrainer;

public class ModelTrainer {

    static public Model<Label> trainModel(MutableDataset trainingDataset) {
        Trainer<Label> trainer = new LogisticRegressionTrainer();
        System.out.println(trainer);
        Model<Label> irisModel = trainer.train(trainingDataset);
        return irisModel;
    }

}
