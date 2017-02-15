/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.http.route2;

import be.nille.http.route2.Path;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 *
 * @author nholvoet
 */
@Slf4j
public class PathTest {
    
    @Test
    public void pathShouldMatchWhenSameAsInURL(){
        Path path = new Path("/subscriber/1/search");
        String pathString = "/subscriber/1/search";
        assertTrue(path.matches(pathString));
    }
    
    @Test
    public void pathShouldMatchWhenSameAsInURLWithParameters(){
        Path path = new Path("/subscriber/1/search");
        String pathString = "/subscriber/1/search?hello=p";
        assertTrue(path.matches(pathString));
    }
    
    @Test
    public void pathShouldMatchWhenSameAsInURLWithPathParam(){
        Path path = new Path("/subscriber/{subscriberId}/search");
        String pathString = "/subscriber/1/search";
        assertTrue(path.matches(pathString));
    }
    
    @Test
    public void pathShouldNotMatchWhenDifferentAsInUrl(){
        Path path = new Path("/subscriber/1/search");    
        String pathString = "/subscription/2/search";
        assertFalse(path.matches(pathString));
    }
    
    @Test
    public void pathShouldNotMatchWhenDifferentStart(){
        Path path = new Path("/subscriber/1/search");    
        String pathString = "subscriber/1/search";
        assertFalse(path.matches(pathString));
    }
    
    
    @Test
    public void pathShouldNotMatchWhenDifferentEnd(){
        Path path = new Path("/subscriber/1/search");    
        String pathString = "/subscriber/1/searchp";
        assertFalse(path.matches(pathString));
    }
    
    @Test
    public void shouldNotHavePathVariables(){
        Path path = new Path("/subscriber/1/search");    
        assertTrue(path.getVariables().isEmpty());
    }
    
    @Test
    public void shouldHaveOnePathVariable(){
        Path path = new Path("/subscriber/{subscriptionId}/search");    
        assertTrue(path.getVariables().size() == 1);
       
    }
    
    @Test
    public void shouldHaveTwoPathVariables(){
        Path path = new Path("/subscriber/{subscriber}/{subscriptionId}/search");    
        assertTrue(path.getVariables().size() == 2);
       
    }
}