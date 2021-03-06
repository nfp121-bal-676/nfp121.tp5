
package question1;

public class EnsembleTest extends junit.framework.TestCase {

    public void testUnion() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));

        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));

        question1.Ensemble<Integer> union = e1.union(e2);
        assertEquals(3, union.size());
        assertTrue(union.contains(2));
        assertTrue(union.contains(3));
        assertTrue(union.contains(4));
    }
    
    public void testAdd() {
        Ensemble<Integer> e1 = new Ensemble();
        Ensemble<Integer> e2 = new Ensemble();
        assertEquals(true, e1.add(1));
        assertEquals(true, e1.add(2));
        assertEquals(2, e1.size());
        assertEquals(true, e2.add(2));
        assertEquals(true, e2.add(3));
        assertEquals(2, e2.size());
        assertEquals(false, e1.add(1));
        assertEquals(2, e1.size());
        assertTrue(e1.contains(1));
        assertTrue(e1.contains(2));
        assertTrue(e2.contains(3));
        assertEquals(true, e1.add(6));
        assertEquals(3, e1.size());
    }
    
    public void testInter() {
        Ensemble<Integer> e1 = new Ensemble();
        Ensemble<Integer> e2 = new Ensemble();
        assertEquals(true, e1.add(1));
        assertEquals(true, e1.add(2));
        
        assertEquals(true, e2.add(2));
        assertEquals(true, e2.add(3));
        
        Ensemble<Integer> inter = e1.inter(e2);
        assertEquals(1, inter.size());
        assertFalse(inter.contains(1));
        assertTrue(inter.contains(2));
        assertFalse(inter.contains(3));
    }
    public void testDiff() {
        Ensemble<Integer> e1 = new Ensemble();
        Ensemble<Integer> e2 = new Ensemble();
        assertEquals(true, e1.add(1));
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(10));

        assertEquals(true, e2.add(2));
        assertEquals(true, e2.add(3));

        Ensemble<Integer> diff = e1.diff(e2);
        assertEquals(2, diff.size());
        assertTrue(diff.contains(1));
        assertFalse(diff.contains(2));
        assertFalse(diff.contains(3));
        assertTrue(diff.contains(10));
    }
    
    public void testDiffSym() {
        Ensemble<Integer> e1 = new Ensemble();
        Ensemble<Integer> e2 = new Ensemble();
        assertEquals(true, e1.add(1));
        assertEquals(true, e1.add(2));

        assertEquals(true, e2.add(2));
        assertEquals(true, e2.add(3));

        question1.Ensemble<Integer> diffSym = e1.diffSym(e2);
        assertEquals(2, diffSym.size());
        assertTrue(diffSym.contains(1));
        assertFalse(diffSym.contains(2));
        assertTrue(diffSym.contains(3));
    }
}
