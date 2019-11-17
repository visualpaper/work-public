import sys


class B(Exception):
    pass


class C(B):
    pass


class D(C):
    pass


# [例外]
# raise が throw と思って良い
# try ~ except で制御
# 上から下に except が評価されるため、継承クラスの場合の順序には注意。
for cls in [B, C, D]:
    try:
        raise cls()
    except D:
        print("D")
    except C:
        print("C")
    except B:
        print("B")
    except (RuntimeError, TypeError, NameError) as err:
        print("error: {0}".format(err))
        pass
    except:
        print("Unexpected error:", sys.exc_info()[0])
        pass
