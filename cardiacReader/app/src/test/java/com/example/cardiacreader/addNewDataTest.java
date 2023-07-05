package com.example.cardiacreader;



import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Test;


public class addNewDataTest {


        String curr_date = "06/07/2023";
        String curr_time = "23:53";

        /**
         * Testing addData method
         */
        @Test
        public void testAddData() {
            addNewData dataList = new addNewData();
            Records data1 = new Records(130, 78, "06/07/2023", "23:53", 88, "Seems fine");
            dataList.addData(data1);
            assertEquals(1, dataList.getData().size());

            Records data2 = new Records(122, 71, "06/07/2023", "23:53", 98, "Heart rate is not fine");
            dataList.addData(data2);
            assertEquals(2, dataList.getData().size());

            assertTrue(dataList.getData().contains(data1));
            assertTrue(dataList.getData().contains(data2));
        }

        /**
         * Testing deleteData method
         */
        @Test
        public void testDeleteData() {
            addNewData dataList = new addNewData();
            Records data1 = new Records(130, 78, "06/07/2023", "23:53", 88, "Seems fine");
            dataList.addData(data1);
            assertEquals(1, dataList.getData().size());

            Records data2 = new Records(122, 71, "06/07/2023", "23:53", 98, "Heart rate is not fine");
            dataList.addData(data2);
            assertEquals(2, dataList.getData().size());

            assertTrue(dataList.getData().contains(data1));
            assertTrue(dataList.getData().contains(data2));

            dataList.deleteData(data1);
            assertEquals(1, dataList.getData().size());
            assertFalse(dataList.getData().contains(data1));

            dataList.deleteData(data2);
            assertEquals(0, dataList.getData().size());
            assertFalse(dataList.getData().contains(data2));
        }

        /**
         * Testing addData method for exceptions
         */
        @Test
        public void testAddRecordException() {
            addNewData dataList = new addNewData();
            Records data1 = new Records(130, 78, "06/07/2023", "23:53", 88, "Seems fine");
            dataList.addData(data1);

            assertThrows(IllegalArgumentException.class, () -> dataList.addData(data1));
        }

        /**
         * Testing deleteData method for exceptions
         */
        @Test
        public void testDeleteRecordException() {
            addNewData dataList = new addNewData();
            Records data1 = new Records(130, 78, "06/07/2023", "23:53", 88, "Seems fine");
            dataList.addData(data1);

            dataList.deleteData(data1);

            assertThrows(IllegalArgumentException.class, () -> dataList.deleteData(data1));
        }
    }
