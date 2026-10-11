public class SinglyLinkedListDemo {
    public static void main(String[] arg) {
        SinglyLinkedList list = new SinglyLinkedList();
        assert list.isEmpty();
        assert list.isEmpty() && list.count() == 0;
        assert list.toString().isEmpty();

        /* Test insert function */
        list.insertHead(5);
        list.insertHead(7);
        list.insertHead(10);
        list.insert(3);
        list.insertNth(1, 4);
        assert list.toString().equals("10->7->5->3->1");
        System.out.println(list);

        /* Test search function */
        assert list.search(10) && list.search(5) && list.search(1) && !list.search(100);

        /* Test get function */
        assert list.getNth(0) == 10 && list.getNth(2) == 5 && list.getNth(4) == 1;

        /* Test delete function */
        list.deleteHead();
        list.deleteNth(1);
        list.delete();
        assert list.toString().equals("7->3");
        System.out.println(list);
        System.out.println(list.search(7));
        assert list.size() == 2 && list.size() == list.count();
        list.clear();
        assert list.isEmpty();
        System.out.println(list);

        try {
            list.delete();
            assert false; /* this should not happen */
        } catch (Exception e) {
            assert true; /* this should happen */
        }
    }
}
