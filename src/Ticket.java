public class Ticket {
    private final int row;
    private final int seat;
    private final double price;
    private final Person person;

    public Ticket(int row,int seat,double price,Person person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;
    }
    public void print(){
        System.out.println("Name:       "+ person.getName());
        System.out.println("SurName:   "+ person.getSurname());
        System.out.println("Email:      "+person.getEmail());
        System.out.println("Row:        "+row);
        System.out.println("Seat:       "+seat);
        System.out.println("Price:      "+price);
    }
}
