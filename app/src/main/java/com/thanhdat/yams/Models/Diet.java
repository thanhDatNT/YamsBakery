package com.thanhdat.yams.Models;

public class Diet {
 private int DietBackGround;
 private int DietThumb;
 private int DietFavorite;
 private String DietName;
 private String DietPrice;
 private String DietContent;
 private String DietRate;
 private String DietQuantityRate;

 public Diet(int dietBackGround, int dietThumb, int dietFavorite, String dietName, String dietPrice, String dietContent, String dietRate, String dietQuantityRate) {
  DietBackGround = dietBackGround;
  DietThumb = dietThumb;
  DietFavorite = dietFavorite;
  DietName = dietName;
  DietPrice = dietPrice;
  DietContent = dietContent;
  DietRate = dietRate;
  DietQuantityRate = dietQuantityRate;
 }

 public int getDietBackGround() {
  return DietBackGround;
 }

 public void setDietBackGround(int dietBackGround) {
  DietBackGround = dietBackGround;
 }

 public int getDietThumb() {
  return DietThumb;
 }

 public void setDietThumb(int dietThumb) {
  DietThumb = dietThumb;
 }

 public int getDietFavorite() {
  return DietFavorite;
 }

 public void setDietFavorite(int dietFavorite) {
  DietFavorite = dietFavorite;
 }

 public String getDietName() {
  return DietName;
 }

 public void setDietName(String dietName) {
  DietName = dietName;
 }

 public String getDietPrice() {
  return DietPrice;
 }

 public void setDietPrice(String dietPrice) {
  DietPrice = dietPrice;
 }

 public String getDietContent() {
  return DietContent;
 }

 public void setDietContent(String dietContent) {
  DietContent = dietContent;
 }

 public String getDietRate() {
  return DietRate;
 }

 public void setDietRate(String dietRate) {
  DietRate = dietRate;
 }

 public String getDietQuantityRate() {
  return DietQuantityRate;
 }

 public void setDietQuantityRate(String dietQuantityRate) {
  DietQuantityRate = dietQuantityRate;
 }
}
