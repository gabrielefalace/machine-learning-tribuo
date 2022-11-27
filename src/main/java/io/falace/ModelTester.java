package io.falace;

import org.tribuo.Model;
import org.tribuo.MutableDataset;
import org.tribuo.classification.Label;
import org.tribuo.classification.evaluation.LabelEvaluator;

public class ModelTester {

    static public void evaluateModel(Model<Label> model, MutableDataset testDataset) {
        var evaluator = new LabelEvaluator();
        var evaluation = evaluator.evaluate(model,testDataset);
        System.out.println(evaluation.toString());
    }

}
