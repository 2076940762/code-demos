package hello

import java.util.Arrays
import util.control.Breaks._;

object IfWhileFor {

  def main(args: Array[String]): Unit = {

    //if
    var a = if (10000 > 0) {
      1;
    } else {
      0;
    }
    println(s"a=$a");

    //for
    for (i <- 1 to 99999 by 321 if (i % 7 == 0)) {
      println(i);
    }

    for (i <- 1 to 9; j <- 1 to 9) {
      println(s"i=$i;j=$j = " + i * j);
    }

    var v = for (i <- Array(1, 2, 3, 4, 5, 6, 7, 8, 95, 44, 33)) yield {
      println(s"i=$i");
      i;
    }
    println(s"v=$v\n" + Arrays.toString(v));

    /**
     * break
     */
    breakable {
      for (i <- 1 to 100) {
        println(i);

        if (i == 10) {
          break; //java break
        }
      }
    }

    for (i <- 1222 to 4444 by 111) {
      println(i);

      breakable {
        if (i % 321 == 0) {
          break; //continue
        }
      }
    }

    println("~~o^0^o~~~~o^0^o~~~~o^0^o~~~~o^0^o~~~~o^0^o~~");

    //case
    println("请输入一个字符");
    var ch = readChar();
    ch match {
      case 'A' => println("85-100");
      case 'B' => println("70-84");
      case 'D' => println("60-69");
      case 'D' => println("0-60");
      case _ => println("~~~~~~~~~~~");
    }

    for (ele <- List(1, 2, 3, 4.123, true, "scala")) {
      ele match {
        case i: Int => println("Int");
        case d: Double => println("Double")
        case str: String => println("string")
        case b: Boolean => println("Boolean")
        case _ => println(" i do not konwn");
      }
    }

    for (ele <- List(1, 2, 3, 4.123, true, "scala")) {
      ele match {
        case i: Int if(i%2 == 0) => println("even Int");
        case i: Int if(i%2 == 1) => println("odd Int");
        case d: Double => println("Double")
        case str: String => println("string")
        case b: Boolean => println("Boolean")
        case _ => println(" i do not konwn");
      }
    }

  } //main

}