
import os
import logging
import consts
import sys

logger = logging.getLogger()

def lambda_handler(event, context):

    # [config 外部ファイル Ver]
    # import にて情報取得
    print(consts.AWS_ES['host'])
    print(consts.AWS_ES['region'])


    # [環境変数情報]
    # os にて情報取得
    print(os.environ['DELETE_DAY'])
    print(os.environ['DELETE_HOUR'])


    # [cotext]
    # lambda 実行情報
    print("Log stream name:", context.log_stream_name)
    print("Log group name:",  context.log_group_name)
    print("Request ID:",context.aws_request_id)
    print("Mem. limits(MB):", context.memory_limit_in_mb)
    print("Time remaining (MS):", context.get_remaining_time_in_millis())

    # [return]
    # 処理を途中で返却したければ return
    # この場合は、この時点で lambda 関数終了と看做され、succeed になる。
    # ただ、何も返さないと実行結果が「null」になるので何か出すべきだと思う。
    # return


    # 基本的な例外処理
    # ※ try 構文で catch しない場合は lambda 実行結果が failed になる。(例1)
    # ※ 自分で例外を発生 (raise) しても failed になる。(例2)
    # ⇒ それ以外は全部 succeed

    # 例1
    # consts2.AWS = 'aaa'

    try:
        consts2.AWS = 'aaa'
    except:
        # [logger]
        # 何も出さない場合と、stacktrace 込み
        # ※ フォーマットも変更できるらしい。
        logger.error("INFO0001")
        logger.error("SAMPLE0001", exc_info=True)

        # (例2)
        # raise Exception('error');


    return 'Hello from Lambda'
