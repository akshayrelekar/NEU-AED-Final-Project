/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.data.analytics;

import app.data.Network;
import app.data.directories.Directory;
import app.entities.Rating;
import app.entities.user.Lawyer;
import app.entities.workqueues.GrievanceRequest;
import app.entities.workqueues.WorkItem;
import app.entities.workqueues.WorkQueue;
import app.utils.charts.ChartUtils;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author PC
 */
public class AnalyticsHelper {
    
    public static int calulateRating(Directory<String, Rating> ratings){
        
        if(ratings.size() == 0){
            return 1;
        }
        
        int total = 0;
        for(Rating r: ratings.getAllEntries()){
            total = total + r.getValue();
        }
        total = total / ratings.size();
        return total;
    }
    
    
    public static JPanel getStarVsClientChart(Lawyer lawyer){
        int[] rateArr = new int[5];
        for(Rating r:lawyer.getRatings().getAllEntries()){
            rateArr[r.getValue()-1]++;
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue( rateArr[0], "1" , "1" );
        dataset.addValue( rateArr[1], "2" , "2" );
        dataset.addValue( rateArr[2], "3" , "3" ); 
        dataset.addValue( rateArr[3], "4" , "4" ); 
        dataset.addValue( rateArr[4], "5" , "5" ); 
        
        return ChartUtils.getBarChart("Stars Vs Clients", "Stars", "Clients", dataset);
    }
    
    public static JPanel getPieChart(Lawyer lawyer){
        int pending=0, done=0;
        WorkQueue workQueue =  lawyer.getWorkqueue();
        List<WorkItem> list = workQueue.getWorkList();
        for(WorkItem req : list){
            GrievanceRequest r = (GrievanceRequest) req;
            
            if(r.getStatus().equals("PENDING")){
                pending++;
            }
            else{
                done++;
            }
        }
        
        DefaultPieDataset datasetPie = new DefaultPieDataset();
        datasetPie.setValue("Open Requests", pending);
        datasetPie.setValue("Resolved Requests", done);

        return ChartUtils.getPieChart("Pending Vs. Closed Requests",datasetPie);
    }
    
    public static Integer getRanking(Lawyer lawyer){
        List<Lawyer> lawyerList = Network.getInstance().getLAWYER_DIRECTORY().getAllEntries();
        Collections.sort(lawyerList, SorterFactory.RATING_SORTER.getSorter());
        
        return (lawyerList.indexOf(lawyer)+1);
    }
}
