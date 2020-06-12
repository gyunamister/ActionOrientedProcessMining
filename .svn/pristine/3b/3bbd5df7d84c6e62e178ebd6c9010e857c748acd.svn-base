package org.processmining.actionorientedprocessmining.constraintmonitor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.processmining.actionorientedprocessmining.event.EventStream;
import org.processmining.actionorientedprocessmining.event.TimeMoment;

public class ConstraintMonitor {
	CMConfig cmConfig;
	Derivation calculator;
	Evaluation evaluator;

	public ConstraintMonitor(CMConfig cmConfig) {
		calculator = new Derivation();
		evaluator = new Evaluation();
		this.cmConfig=cmConfig;
	}


	public Set<ConstraintInstance> monitor(int currentTime, EventStream es) {
		Set<ConstraintInstance> outputs = new HashSet<ConstraintInstance>();
		if(es.getEventSet().size()!=0) {
			for(Constraint c : this.cmConfig.getConstraintSet()) {
				Set<EvalResult> evalResults=new HashSet<EvalResult>();
				for(TimeMoment tm: c.getTM()) {
					if(tm.getTime()==currentTime) {
						evalResults = c.getCF().apply(es.getEventSet(), tm.getTimeWindow());
					}
				}
				for(EvalResult evalResult:evalResults) {
					ConstraintInstance ci = new ConstraintInstance(c.getCF().cfName,evalResult.getContext(), currentTime,evalResult.outc);
					outputs.add(ci);
				}
			}
		}
		this.recordCI(outputs, currentTime);
		return outputs;
	}

	public void recordCI(Set<ConstraintInstance> cis, int currentTime) {
		if(currentTime == 1) {
			String dirName = String.format("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/constraint-log-%s.csv",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			Path file = Paths.get(dirName);
			try {
				String temp="";
				List<String> lines = Arrays.asList(temp);
				Files.write(file, lines, StandardCharsets.UTF_8);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
		for(ConstraintInstance ci:cis) {
			List<String> lines = Arrays.asList(ci.toString());
			String dirName = String.format("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/constraint-log-%s.csv",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			Path file = Paths.get(dirName);
			try {
				Files.write(file, lines, StandardOpenOption.APPEND);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
	}

}
