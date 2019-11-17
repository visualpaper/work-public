

# [Class]
# private に見せるための慣習はあるが、全て public。
# m = Mapping() のようにインスタンス化する。
# init で初期化ができる。
class Mapping:

    # これはインスタンス別に同じ参照となる (static と思えば良い)
    sample = []

    # init でインスタンス化時の処理が記載できる
    def __init__(self, iterable):
        self.items_list = []
        self.__update(iterable)

    def update(self, iterable):
        for item in iterable:
            self.items_list.append(item)

    __update = update   # private copy of original update() method


# 継承は以下で可能
# 多重継承もできる。MappingSubclass(Mapping, AAAA, BBB)
class MappingSubclass(Mapping):

    def update(self, keys, values):
        # provides new signature for update()
        # but does not break __init__()
        for item in zip(keys, values):
            self.items_list.append(item)
