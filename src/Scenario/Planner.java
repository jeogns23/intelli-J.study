package Scenario;

import java.util.Random;
import java.util.Scanner;

public class Planner { //각 변수들과 배열 생성
    private String[] continent, asia, africa, europe, northAmerica, southAmerica; //대륙 및 국가 배열
    private String[] airLines, transportation, accommodation;  //항공사, 대중교통, 숙박 배열
    private String[] countryArray;
    private String choiceContinent, choiceCountry, choiceAirline, choiceTransportation, choiceAccommodation, schedule;
    private int index, nums, choicePriceCountry, choicePriceAirline, choicePriceTransportation, choicePriceAccommodation, budget;
    private int[] priceAsia, priceAfrica, priceEurope, priceNorthAmerica, priceSouthAmerica, priceAirlines, priceTransportation, priceAccommodation;
    private int[] priceArray;
    public Planner() { //생성자에 변수 초기화
        this.index = 0;
        this.nums = 0;
        this.continent = new String[] {"아시아", "아프리카", "유럽", "북아메리카", "남아메리카"};
        this.asia = new String[] {"일본", "필리핀", "베트남", "인도", "싱가포르"};
        this.africa = new String[] {"가나", "수단", "앙골라", "탄자니아", "세네갈"};
        this.europe = new String[] {"그리스", "영국", "이탈리아", "스위스", "프랑스"};
        this.northAmerica = new String[] {"미국", "캐나다", "멕시코"};
        this.southAmerica = new String[] {"아르헨티나", "브라질", "페루", "베네수엘라", "파라과이"};
        this.airLines = new String[] {"대한항공", "아시아나", "진에어", "티웨이항공", "에어프레미아"};
        this.transportation = new String[] {"공항버스", "국내선", "KTX", "택시"};
        this.accommodation = new String[] {"5성급 호텔", "저렴한 모텔", "게스트하우스", "찜질방", "노숙"};
        this.priceAsia = new int[] {50, 100, 120, 170, 150};
        this.priceAfrica = new int[] {250, 180, 230, 200, 240};
        this.priceEurope = new int[] {130, 150, 200, 250, 220};
        this.priceNorthAmerica = new int[] {300, 250, 280};
        this.priceSouthAmerica = new int[] {350, 320, 300, 340, 310};
        this.priceAirlines = new int[] {120, 100, 80, 80, 60};
        this.priceTransportation = new int[] {8, 20, 7, 15};
        this.priceAccommodation = new int[] {200, 100, 50, 10, 0};

    }
    public String getChoiceContinent() {
        return choiceContinent;
    }
    public String getChoiceCountry() {
        return choiceCountry;
    }
    public String getChoiceAirLine() {
        return choiceAirline;
    }
    public String getChoiceTransportation() {
        return choiceTransportation;
    }
    public String getChoiceAccommodation() {
        return choiceAccommodation;
    }
    public int getChoicePriceCountry() {
        return choicePriceCountry;
    }
    public int getChoicePriceAirline() {
        return choicePriceAirline;
    }
    public int getChoicePriceTransportation() {
        return choicePriceTransportation;
    }
    public int getChoicePriceAccommodation() {
        return choicePriceAccommodation;
    }
    public String getSchedule() {
        return schedule;
    }
    public int getBudget() {
        return budget;
    }

    public void randomContinents() {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("      일정과 예산 입력이 완료되었습니다.");
        System.out.println("  국가를 선택하기 앞서 랜덤으로 대륙이 추첨됩니다.");
        System.out.println("    맘에 들지 않으면 다시 추첨할 수 있습니다.");
        System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        String input;
        do {
            int choice = rand.nextInt(5); //랜덤으로 대륙 배열 인덱스값 추첨
            choiceContinent = continent[choice];
            System.out.println(" ✈ 축하합니다 랜덤으로 " + choiceContinent + " 이(가) 뽑혔습니다");
            System.out.println(choiceContinent + " 로(으로) 진행하시겠습니까? ");
            System.out.println("진행하기 > y    다시추첨 > x");
            System.out.print("입력 >>  ");
            input = sc.nextLine();

            if("y".equals(input))
                break;

        }while(!("x".equals(input))||!("y".equals(input)));
    }
    public void printCountries(String continents) {
        System.out.println(" 선택하신 " + continents + " 의 국가들입니다.");
        System.out.println(" 이 곳들 중에 가고 싶은 나라를 골라주세요");
        switch(continents) {
            case "아시아" :
                countryArray = asia;
                priceArray = priceAsia;
                break;
            case "아프리카" :
                countryArray = africa;
                priceArray = priceAfrica;
                break;
            case "유럽" :
                countryArray = europe;
                priceArray = priceEurope;
                break;
            case "북아메리카" :
                countryArray = northAmerica;
                priceArray = priceNorthAmerica;
                break;
            case "남아메리카" :
                countryArray = southAmerica;
                priceArray = priceSouthAmerica;
                break;
        }
        //대륙별로 저장된 국가 배열 리스트 출력
        for (int i = 0; i < countryArray.length; i++)
            System.out.printf("%d.%s(%d만원) ", i + 1, countryArray[i], priceArray[i]);
        System.out.println();
    }
    public void choiceCounties(String continents) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("번호 선택 >>  ");
            this.index = sc.nextInt();
            if(this.index <= 0 || this.index > countryArray.length) {
                System.out.println("입력 범위를 벗어났습니다");
                continue;
            }
            System.out.println("▶ 선택된 나라는 " + countryArray[this.index-1] + "(" + priceArray[this.index-1] + "만원) 입니다 ");
            System.out.println("다시선택 > 0    선택완료 > 1");
            System.out.print("입력 >>  ");
            this.nums = sc.nextInt();
            if (this.nums == 1)
                break;
            switch(continents) {
                case "아시아" :
                    choicePriceCountry = priceAsia[this.index-1];
                    break;
                case "아프리카" :
                    choicePriceCountry = priceAfrica[this.index-1];
                    break;
                case "유럽" :
                    choicePriceCountry = priceEurope[this.index-1];
                    break;
                case "북아메리카" :
                    choicePriceCountry = priceNorthAmerica[this.index-1];
                    break;
                case "남아메리카" :
                    choicePriceCountry = priceSouthAmerica[this.index-1];
                    break;
            }
        } while (this.nums == 0);
        choiceCountry = countryArray[this.index-1];
        choicePriceCountry = priceArray[this.index-1];
    }
    public void choiceTransports() {
        Scanner sc = new Scanner(System.in);
        System.out.println("  <<다음은 공항까지 이동할 교통수단 선택입니다>>");
        System.out.println(" 아래의 리스트를 보고 원하는 교통수단을 선택해주세요");
        //입력된 교통수단 배열 출력
        for (int i = 0; i < transportation.length; i++)
            System.out.printf("%d.%s(%d만원)  ", i + 1, transportation[i], priceTransportation[i]);
        System.out.println();
        do {
            System.out.print("번호 선택 >>  ");
            this.index = sc.nextInt();
            if(this.index <= 0 || this.index > transportation.length) {
                System.out.println("입력 범위를 벗어났습니다");
                continue;
            }
            System.out.println("▶ 선택된 교통수단은 " + transportation[this.index-1] + "(" + priceTransportation[this.index-1] + "만원) 입니다 ");
            System.out.println("다시선택 > 0    선택완료 > 1");
            System.out.print("입력 >>  ");
            this.nums = sc.nextInt();
            if (this.nums == 1)
                break;
            else if (this.nums == 0)
                continue;
            else{
                System.out.println("!!0과 1중에서 입력해주세요");
                continue;
            }

        } while (true);
        choiceTransportation = transportation[this.index-1];
        choicePriceTransportation = priceTransportation[this.index-1];
    }
    public void choiceAirlines() {
        Scanner sc = new Scanner(System.in);
        System.out.println(" 선택하신 여행지로 갈 때 이용할 항공사를 선택해주세요");
        //항공사 배열 출력
        for (int i = 0; i < airLines.length; i++)
            System.out.printf("%d.%s(%d만원) ", i + 1, airLines[i], priceAirlines[i]);
        System.out.println();
        do {
            System.out.print("번호 선택 >>  ");
            this.index = sc.nextInt();
            if(this.index <= 0 || this.index > airLines.length) {
                System.out.println("!!입력 범위를 벗어났습니다");
                continue;
            }
            System.out.println("▶ 선택된 항공사는 " + airLines[this.index-1] + "(" + priceAirlines[this.index-1] + "만원) 입니다 ");
            System.out.println("다시선택 > 0    선택완료 > 1");
            System.out.print("입력 >>  ");
            this.nums = sc.nextInt();
            if(this.nums == 1)
                break;
            else if (this.nums  != 0) {
                System.out.println("!!0과 1중에서 입력해주세요");
                continue;
            }
        }while(!(this.nums  == 0 && this.nums  ==1));
        choiceAirline = airLines[this.index-1];
        choicePriceAirline = priceAirlines[this.index-1];
    }
    public void randomAccommodation() {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int luckyTest = 2;
        int count = 1;
        int choice;
        System.out.println("         ** 이제는 당신의 운을 시험해볼 차례입니다 **");
        System.out.println("         아래 리스트는 여행지에서 사용할 숙박시설입니다");
        System.out.println(" 기회는 2번, 두번의 뽑기를 통해 선택된 숙박시설을 이용해야 합니다 O_o;");
        System.out.println("      처음에 나온 숙박시설이 맘에 들면 바로 종료할 수 있습니다");
        System.out.println("              ↓ 아래 숙박시설을 참고해주세요 ↓");
        //숙박시설 배열 출력
        for (int i = 0; i < accommodation.length; i++)
            System.out.printf("%d.%s(%d만원)   ", i + 1, accommodation[i], priceAccommodation[i]);
        System.out.println();
        System.out.println(" 0번을 누르시면 추첨이 시작됩니다!! ");
        System.out.print("입력 >>  ");
        this.nums = sc.nextInt();
        do{
            choice = rand.nextInt(5); //랜덤으로 숙박시설 배열 인덱스값 추첨
            choiceAccommodation = accommodation[choice];
            System.out.printf(" %d번째 뽑힌 숙박시설은 %s 입니다\n", count, choiceAccommodation);
            System.out.println("다시선택 > 0    선택완료> 1");
            System.out.print("입력 >>  ");
            this.nums = sc.nextInt();
            if(this.nums == 0) {
                if(luckyTest > 1) {
                    System.out.printf("남은 기회: %d\n", luckyTest - 1);
                    luckyTest--;
                    count++;
                }else if(count ==2 ) {
                    System.out.println("!!기회를 다 사용하셨습니다 추첨을 종료합니다");
                    break;
                }
            }else if(this.nums == 1)
                break;
        }while (luckyTest >= 1);
        System.out.println("최종 선택된 숙박시설은 " + choiceAccommodation + "(" + priceAccommodation[choice] + "만원) 입니다 \uD83D\uDE04");
        choicePriceAccommodation = priceAccommodation[choice];
    }
    public void scheduleBudget() {
        Scanner sc = new Scanner(System.in);
        String schedule;
        int budget, number;
        System.out.println("여행일정과 예산을 입력해주세요");
        do {
            System.out.print("1. 여행일정 입력 >>  ");
            schedule = sc.nextLine();
            System.out.print("2. 예산 입력(단위:만원) >>  ");
            budget = sc.nextInt();
            System.out.println();
            System.out.println(" ✓입력하신 일정: " + schedule);
            System.out.println(" ✓입력하신 예산: " + budget + " 만원");
            System.out.println("다시입력 > 0    입력완료 > 1");
            System.out.print("입력 >>  ");
            number = sc.nextInt();
            sc.nextLine();
            if(number == 1)
                break;
            else if (number != 0) {
                System.out.println("!!0과 1중에서 입력해주세요");
                continue;
            }
        }while(!(number == 0 && number ==1));
        this.schedule = schedule;
        this.budget = budget;
    }

}
