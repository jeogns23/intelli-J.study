package Scenario;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class ProgramTravel {

    public static void main(String[] args) throws  IOException  {
        Planner planner = new Planner(); //Planner 객체 생성
        Scanner sc = new Scanner(System.in);
        String selectedContinent, selectedCountry, selectedTransportation, selectedAirline, selectedAccommodation, schedule;
        int selectedPriceCountry, selectedPriceTransportation, selectedPriceAirline, selectedPriceAccommodation, budget, total;
        do {
            startMenu(); //시작메뉴 노출
            System.out.println();

            planner.scheduleBudget(); //여행일정, 예산 사용자 입력함수 호출
            System.out.println();

            planner.randomContinents(); //랜덤으로 대륙 6곳중 한곳 추첨하는 함수 호출
            System.out.println();

            selectedContinent = planner.getChoiceContinent(); //continent 클래스에서 랜덤으로 선택된 대륙이름 가져오기
            planner.printCountries(selectedContinent); //선택된 대륙에 따라 저장된 국가 배열 출력 함수 호출
            planner.choiceCounties(selectedContinent); //국가 리스트 확인후 국가 선택 함수 호출
            selectedCountry = planner.getChoiceCountry(); //선택된 국가 정보 가져오기
            selectedPriceCountry = planner.getChoicePriceCountry(); //국가 금액 가져오기
            System.out.println();

            planner.choiceTransports(); //교통수단 선택 함수 호출
            selectedTransportation = planner.getChoiceTransportation(); //선택된 교통수단 정보 가져오기
            selectedPriceTransportation = planner.getChoicePriceTransportation(); //교통수단 금액 가져오기
            System.out.println();

            planner.choiceAirlines(); //항공사 선택 함수 호출
            selectedAirline = planner.getChoiceAirLine(); //선택된 항공사 정보 가져오기
            selectedPriceAirline = planner.getChoicePriceAirline(); //항공사 금액 가져오기
            System.out.println();

            planner.randomAccommodation(); //숙박시설 선택 (기회주고 랜덤으로 선택) 함수 호출
            selectedAccommodation = planner.getChoiceAccommodation(); //선택된 숙박시설 정보 가져오기
            selectedPriceAccommodation = planner.getChoicePriceAccommodation(); //숙박시설 금액 가져오기
            System.out.println();

            schedule = planner.getSchedule(); //입력한 일정 갖고오기
            budget = planner.getBudget(); //입력한 예산 정보 가져오기
            total = selectedPriceCountry + selectedPriceTransportation + selectedPriceAirline + selectedPriceAccommodation; //경비들 총 합계
            //전체금액과 입력한 예산 비교해서 예산이 모자라면 추가로 마일리지 뽑기
            System.out.println("현재까지의 경비: " + total + " 만원");
            if (budget > total) { //예산이 총 경비보다 클 경우
                System.out.println("당신이 입력한 예산으로는 충분합니다.");
                System.out.println("진행하신 일정을 저장하시겠습니까?");
                System.out.println("저장하기 > 0    처음으로 > 1");
                System.out.print("입력 >>  ");
                int num = sc.nextInt();
                switch(num) {
                    case 0 :{
                        FileOutputStream fos = new FileOutputStream("res/plan.txt");
                        PrintWriter pw = new PrintWriter(fos);
                        int remain = budget - total;

                        pw.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                        pw.println("               - 완성된 여행 계획 - ");
                        pw.println("입력한 일정: " + schedule + " / 입력한 예산: " + budget + " 만원");
                        pw.println("선택된 대륙: " + selectedContinent + " / 여행지: " + selectedCountry);
                        pw.println("공항까지 이동할 교통수단: " + selectedTransportation);
                        pw.println("여행지까지 이동수단: " + selectedAirline);
                        pw.println("여행지에서 사용할 숙박시설: " + selectedAccommodation);
                        pw.println("선택한 총 경비: " + total + " 만원");
                        pw.println("남는 예산: " + remain + " 만원");
                        pw.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                        pw.println();
                        pw.print("                             ★ Happy Nice Trip ★");

                        pw.close();
                        fos.close();
                        System.out.println("저장이 완료되었습니다");
                        System.out.println("프로그램을 종료합니다");
                        break;
                    }
                    case 1 :
                        break;
                    default :
                        System.out.println("0 과 1 중에서 선택해주세요");
                }
            } else if (budget < total) { //예산이 총 경비보다 작을 경우
                int mileage = 0;
                int needBudget = total - budget;
                System.out.println("당신이 입력한 예산보다 경비가 " + needBudget + " 만원 모자랍니다.");
                System.out.println("부족한 예산을 메꾸기 위해 마일리지를 요청할 수 있습니다");
                System.out.println("요청 하시겠습니까?");
                System.out.println("요청하기 > 0    이대로저장하기 > 1    처음으로 > 2");
                System.out.print("입력 >>  ");
                int num = sc.nextInt();
                switch(num) {
                    case 0:
                        mileage = requestMileage(); //모자란 금액, 마일리지로 추가 보충 함수 호출
                    case 1:{
                        FileOutputStream fos = new FileOutputStream("res/plan.txt");
                        PrintWriter pw = new PrintWriter(fos);
                        int needBudget2 = total - budget - mileage;

                        pw.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                        pw.println("               - 완성된 여행 계획 - ");
                        pw.println("입력한 일정: " + schedule + " / 입력한 예산: " + budget + " 만원");
                        pw.println("선택된 대륙: " + selectedContinent + " / 여행지: " + selectedCountry);
                        pw.println("공항까지 이동할 교통수단: " + selectedTransportation);
                        pw.println("여행지까지 이동수단: " + selectedAirline);
                        pw.println("여행지에서 사용할 숙박시설: " + selectedAccommodation);
                        pw.println("선택한 총 경비: " + total + " 만원");
                        pw.println("요청한 마일리지: " + mileage + " 만원");
                        pw.println("모자란 예산: " + needBudget2 + " 만원");
                        pw.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                        pw.println();
                        pw.print("                             ★ Happy Nice Trip ★");

                        pw.close();
                        fos.close();
                        System.out.println("저장이 완료되었습니다");
                        System.out.println("프로그램을 종료합니다");
                        break;
                    }
                    case 2:
                        break;
                    default:
                        System.out.println("0, 1, 2 중에서 선택해주세요");
                }
            }

        } while (true);

    }




    private static int requestMileage() {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int[] mileage = {50, 60, 70, 80, 90, 100}; //랜덤으로 추첨될 마일리지 금액 배열 생성
        int nums , choice, addBudget;
        int totalMileage = 0;
        int request = 3;
        int count = 1;
        System.out.println();
        System.out.println("                >> 마일리지 요청하기 <<");
        System.out.println("마일리지는 50만원에서 100만원까지 10만원 단위로, 총 세번 뽑을 수 있습니다.");
        System.out.println(" 0번을 누르시면 추첨이 시작됩니다!! ");
        System.out.print("입력 >>  ");
        nums = sc.nextInt();
        do{
            choice = rand.nextInt(6); //랜덤으로 마일리지 배열 인덱스값 추첨
            addBudget = mileage[choice];
            System.out.printf(" %d 번째 뽑힌 마일리지는 %d 만원 입니다\n", count, addBudget);
            totalMileage += addBudget; //마일리지 추첨할떄마다 금액 누적
            System.out.println("누적 마일리지: " + totalMileage + " 만원");
            System.out.println();
            System.out.println("다시뽑기 > 0");
            System.out.print("입력 >>  ");
            nums = sc.nextInt();
            if(nums == 0) {
                if (request > 1) {
                    System.out.printf("남은 기회: %d\n", request - 1);
                    request--;
                    count++;
                }
                else
                    break;
            }
        }while (true);
        System.out.println("!!3번의 추첨기회가 끝났습니다.");
        System.out.println("마일리지 금액이 반영된 계획표를 저장합니다.");
        System.out.println();

        return totalMileage;
    }

    private static void startMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        int start;
        do {
            System.out.println("*~*~*~*~*~* Let's go travel *~*~*~*~*~*");
            System.out.println("             여행계획 작성하기 ");
            System.out.println("  시작하기를 선택하면 여행일정과 예산을 입력합니다");
            System.out.println("   입력이 완료되면 몇가지 선택을 할 수 있습니다");
            System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
            System.out.println("**    시작하기 > 0     종료하기 > -1     **");
            System.out.println("      저장된 계획서 보기 > 2");
            System.out.print("입력 >> ");
            start = sc.nextInt();
            switch (start) {
                case 0 :
                    break;
                case -1 :
                    System.out.println("!!프로그램을 종료합니다. 다시 시도해주세요");
                    return;
                case 2 : {
                    FileInputStream fis = new FileInputStream("res/plan.txt");
                    Scanner lineScan = new Scanner(fis);

                    System.out.println(" 저장된 내용 확인하기 ");
                    while (lineScan.hasNextLine()) {
                        String line = lineScan.nextLine();
                        System.out.println(line);
                    }

                    lineScan.close();
                    fis.close();
                    continue;
                }
                default:
                    System.out.println("!!0, -1, 2 중에서 선택해주세요");
                    continue;
            }
            break;

        } while (true);
    }


}
