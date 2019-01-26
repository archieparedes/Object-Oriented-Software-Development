/**
 * @author Archie_Paredes
 * @version 1.0
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssertExp1Test {
    @Test
    void testMinIndex(){
        assertTrue(6 == AssertExp1.minPosition(new double[] { 1, -4, -7, 7, 8, 11, -9 }));
        assertTrue(0 == AssertExp1.minPosition(new double[] { -7 }));
        assertTrue(2 == AssertExp1.minPosition(new double[] { 1, -4, -7, 7, 8, 11 }));
        assertTrue(0 == AssertExp1.minPosition(new double[] { -13, -4, -7, 7, 8, 11}));
    }

    @Test
    void testNumUnique(){
        // test for empty array
        assertTrue(0 == AssertExp1.numUnique(new double[] { }));

        // test for 1 element
        assertTrue(1 == AssertExp1.numUnique(new double[] { 11 }));

        // test for 1 item duplicates
        assertTrue(1 == AssertExp1.numUnique(new double[] { 11, 11, 11, 11 }));

        // test for all unique
        assertTrue(4 == AssertExp1.numUnique(new double[] { 11, 12, 111, 112 }));

        // test for multiple item duplicates
        assertTrue(8 == AssertExp1.numUnique(new double[] {11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 }));
        assertTrue(8 == AssertExp1.numUnique(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 }));
    }

    @Test
    void testremoveDuplicates(){
        // test for empty array
        double[] testArr0 = new double[]{};
        double[] testArr0res1 = AssertExp1.removeDuplicates(new double[] {});
        assertTrue(testArr0.length == testArr0res1.length && testArr0res1.length == 0);

        // test for 1 element
        double[] testArr1 = new double[] {11.0};
        double[] testArr1res1 = AssertExp1.removeDuplicates(new double[] { 11.0, 11.0, 11.0, 11.0 });
        assertTrue(testArr1res1.length == 1 && testArr1[0] == testArr1res1[0]);

        // test for full array
        double[] testArr2 = new double[] { 11.0, 22.0, 33.0, 44.0, 55.0, 66.0, 77.0, 88.0};
        double[] testArr2res1 = AssertExp1.removeDuplicates(new double[] { 11.0, 11.0, 11.0, 11.0, 22.0, 33.0, 44.0, 44.0, 44.0, 44.0, 44.0, 55.0, 55.0, 66.0, 77.0, 88.0, 88.0 });
        double[] testArr2res2 = AssertExp1.removeDuplicates(new double[] {11.0, 22.0, 33.0, 44.0, 44.0, 44.0, 44.0, 44.0, 55.0, 55.0, 66.0, 77.0, 88.0 });
        for(int i = 0; i < testArr2.length; i++){
            assertEquals(testArr2[i], testArr2res1[i]);
            assertEquals(testArr2[i], testArr2res2[i]);
        }

    }

}