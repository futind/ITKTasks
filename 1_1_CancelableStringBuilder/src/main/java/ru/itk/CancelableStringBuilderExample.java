package ru.itk;


public class CancelableStringBuilderExample {
    public static void main(String[] args) {

        CancelableStringBuilder csb = new CancelableStringBuilder("example"); // csb: "example"

        csb.append(" appendix1") // csb: "example appendix1"
                .append(" appendix2") // csb: "example appendix1 appendix2"
                .insert(17, " appendix3") // csb: "example appendix1 appendix3 appendix2"
                .delete(8, 18) // csb: "example appendix3 appendix2"
                .undo(); // csb: "example appendix1 appendix3 appendix2"

        System.out.println(csb);
    }
}