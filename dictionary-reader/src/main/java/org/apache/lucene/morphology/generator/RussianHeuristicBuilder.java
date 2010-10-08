/**
 * Copyright 2009 Alexander Kuznetsov 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.lucene.morphology.generator;

import org.apache.lucene.morphology.dictionary.*;
import org.apache.lucene.morphology.russian.RussianLetterDecoderEncoder;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class RussianHeuristicBuilder {
    public static void main(String[] args) throws IOException {
        GrammarReader grammarInfo = new GrammarReader("dictonary/Dicts/Morph/rgramtab.tab");
        RussianLetterDecoderEncoder decoderEncoder = new RussianLetterDecoderEncoder();
        List<WordFilter> filters = Arrays.asList(new WordStringCleaner(decoderEncoder), new WordCleaner(decoderEncoder));

        DictionaryReader dictionaryReader = new DictionaryReader("dictonary/Dicts/SrcMorph/RusSrc/morphs.mrd", new HashSet<String>(), filters);

        StatisticsCollector statisticsCollector = new StatisticsCollector(grammarInfo, decoderEncoder);
        dictionaryReader.process(statisticsCollector);
        statisticsCollector.saveHeuristic("russian/src/main/resources/org/apache/lucene/morphology/russian/morph.info");

    }
}
