package com.thanhdat.yams.Models;

public class Diet {
 private int DietThumb;
 private String DietName;
 private String DietPrice;
 private String DietContent;
 private Double DietRating;
 private Double DietQuantity;

 public Diet(int dietThumb, String dietName, String dietPrice, String dietContent, Double dietRating, Double dietQuantity) {
  DietThumb = dietThumb;
  DietName = dietName;
  DietPrice = dietPrice;
  DietContent = dietContent;
  DietRating = dietRating;
  DietQuantity = dietQuantity;
 }

 public int getDietThumb() {
  return DietThumb;
 }

 public void setDietThumb(int dietThumb) {
  DietThumb = dietThumb;
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

 public Double getDietRating() {
  return DietRating;
 }

 public void setDietRating(Double dietRating) {
  DietRating = dietRating;
 }

 public Double getDietQuantity() {
  return DietQuantity;
 }

 public void setDietQuantity(Double dietQuantity) {
  DietQuantity = dietQuantity;
 }
}