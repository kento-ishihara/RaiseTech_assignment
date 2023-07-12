import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

//      LinkedHashMapを使用してキー(元素記号)と値(元素名)を登録順にelementNameMapにマッピング
        Map<String, String> elementNameMap = new LinkedHashMap<>();
        elementNameMap.put("H", "水素");
        elementNameMap.put("Na", "ナトリウム");
        elementNameMap.put("He", "ヘリウム");
        elementNameMap.put("Li", "リチウム");
        elementNameMap.put("Be", "ベリリウム");

//      キーから値を検索して登録順に標準出力
        for (String elementSymbol: elementNameMap.keySet()) {
            System.out.println("元素記号：" + elementSymbol + "　元素名：" + elementNameMap.get(elementSymbol));
        }

//      Map.Entryインターフェースを使用してキー(元素記号)と値(元素名)を取り出す
        for (Map.Entry<String, String> entry1 : elementNameMap.entrySet()) {
            System.out.println("元素記号：" + entry1.getKey() + "　元素名：" + entry1.getValue());
        }

        System.out.println("---------------ここからMapインターフェース実装クラスの変更による処理の違いを見てみようの回-----------------");

//      キー(原子番号)と値(元素記号)をelementMapにマッピング
        Map<Integer, String> elementMap = new TreeMap<>();      //TreeMapをHashMapやLinkedHashMapに変えてみると・・・？
        elementMap.put(1, "H");
        elementMap.put(11, "Na");
        elementMap.put(2, "He");
        elementMap.put(3, "Li");
        elementMap.put(4, "Be");

//      キーから値を検索して標準出力
        for (Integer atomicNumberA: elementMap.keySet()) {
            System.out.println("原子番号：" + atomicNumberA + "　元素記号：" + elementMap.get(atomicNumberA));
        }

/*
        原子番号0～14を検索し、原子番号順に標準出力
        マップに値が存在しなければ処理をスキップ
*/
        for (Integer i = 0; i < 15; i++) {
            if (elementMap.get(i) == null) {
                continue;
            }
            System.out.println("原子番号：" + i + "　元素記号：" + elementMap.get(i));
        }

        System.out.println("---------------Mapインターフェース実装クラスのnull値許容確認-----------------");

//      値nullでマッピング
        elementMap.put(119, null);
        elementMap.put(120, null);

//      HashMap、TreeMap、LinkedHashMapはnull値を複数許容
        for (Map.Entry<Integer, String> entry2 : elementMap.entrySet()) {
            System.out.println("原子番号：" + entry2.getKey() + "　元素記号：" + entry2.getValue());
        }

        System.out.println("---------------例外処理1-----------------");

/*      try-catch文を使用した例外処理1
        HashMap、TreeMap、LinkedHashMapそれぞれ試してみると・・・？
*/
        try {
            elementMap.put(null, "Null1だょ");
            for (Integer atomicNumberB: elementMap.keySet()) {
                System.out.println("原子番号：" + atomicNumberB + "　元素記号：" + elementMap.get(atomicNumberB));
            }
        } catch (NullPointerException e) {
            System.out.println("ぬるぽ1");
        } finally {
            System.out.println("ｶﾞｯ");
        }

        System.out.println("---------------例外処理2-----------------");

/*      try-catch文を使用した例外処理2
        HashMapとLinkedHashMapではNullPointerExceptionが発生しないのにnullキーの処理時にthrowしてしまっているので
        間違った処理。HashMap、TreeMap、LinkedHashMapそれぞれ試してみると・・・？
*/
        try {
            elementMap.put(null, "Null2だょ");
            for (Map.Entry<Integer, String> entry3 : elementMap.entrySet()) {
                if (entry3.getKey() != null) {
                    System.out.println("原子番号：" + entry3.getKey() + "　元素記号：" + entry3.getValue());
                } else {
                    throw new NullPointerException();
                }
            }
            } catch (NullPointerException e) {
                System.out.println("ぬるぽ2");
            } finally {
                System.out.println("ｶﾞｯ");
            }

        System.out.println("---------------例外処理3-----------------");

/*      try-catch文を使用した例外処理3
        throwではなくcontinue文でnullキーを除外
        HashMap、TreeMap、LinkedHashMapそれぞれ試してみると・・・？
*/
        try {
            elementMap.put(null, "Null3だょ");
            for (Map.Entry<Integer, String> entry4 : elementMap.entrySet()) {
                if (entry4.getKey() == null) {
                    continue;
                }
                System.out.println("原子番号：" + entry4.getKey() + "　元素記号：" + entry4.getValue());
            }
        } catch (NullPointerException e) {
            System.out.println("ぬるぽ3");
        } finally {
            System.out.println("ｶﾞｯ");
        }

        System.out.println("---------------Mapインターフェース実装クラスのnullキー許容確認-----------------");
        System.out.println("TreeMapはnullキーを許容せず、NullPointerExceptionが発生します！");
        System.out.println("HashMapとLinkedHashMapはnullキーを1つ許容し、NullPointerExceptionが発生しません");

        Integer j = null;
        System.out.println(j + "をキーに設定");

//      nullキーをマッピング
        elementMap.put(j, "ぬるぽι゛ゃなぃょ");
        System.out.println("原子番号：" + j + "　元素記号：" + elementMap.get(j));
    }
}