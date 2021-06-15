package domain;



public class itemDTO {
		private int id;
		private String memberID;
		private int chickenCnt;
		private int waterCnt;
	
     public itemDTO() {
         
      }
      
      public itemDTO(int id, String memberID,  int chickenCnt, int waterCnt) {
         super();
         this.id = id;
         this.memberID= memberID;
         this.chickenCnt = chickenCnt;
         this.waterCnt = waterCnt;
      }
      
      public int getId() {
         return id;
      }
      public void setId(int id) {
         this.id = id;
      }
      public String getMemberID() {
         return memberID;
      }
      public void setMemberID(String memberID) {
         this.memberID = memberID;
      }

      public int getChickenCnt() {
         return chickenCnt;
      }

      public void setChickenCnt(int chickenCnt) {
         this.chickenCnt = chickenCnt;
      }

      public int getWaterCnt() {
         return waterCnt;
      }

      public void setWaterCnt(int waterCnt) {
         this.waterCnt = waterCnt;
      }
   }