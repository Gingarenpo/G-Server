package jp.gingarenpo.gserver.annotation;

/**
 * このアノテーションを付与すると、指定したワールドでのみこのイベントが適用されます。
 * valueにはワールド名を指定しましょう。ワールドが存在しない場合、単に無視されます（つまり永遠に実行されません）。
 */
public @interface OnlyWorldIn {
}
